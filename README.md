# Employee Service

---
### Overview

- Web service that allows administrators to log in and manage employee data

- Administrators send a login request to the Accounts API; they attach the obtained *accessToken* to the *Authorization* header on requests to the Employees API

- Accounts API

  - Creating an Account

    - **URL:** https://localhost:8080/v1/accounts
    - **Method:** POST
    - **URL Request Parameters:**
      - first-name
      - last-name
      - email-address
      - password
      - confirmed-password
    - **Response Status:** 201 Created

  - Logging In

    - **URL:** https://localhost:8080/v1/accounts/login

    - **Method:** POST

    - **URL Request Parameters:**

      - email-address
      - password

    - **Response Status:** 200 OK

    - **Response Body:**

      ```json
      {
         "accessToken": "Bearer zxkjflk..789dfs" 
      }
      ```

- Employees API

  - Create Employee

    - **URL:** https://localhost:8080/v1/employees
    - **Method:** POST
    - **URL Request Parameters:**
      - first-name
      - last-name
      - email-address
      - job-title
      - salary
    - **Response Status:** 201 Created

  - Find All Employees

    - **URL:** https://localhost:8080/v1/employees

    - **Method:** GET

    - **Response Status:** 200 OK

    - **Response Body:**

      ```json
      [
          {
              "id": 1,
              "firstName": "bob",
              "lastName": "doe",
              "emailAddress": "bob.doe@mail.com",
              "title": "manager",
              "salary": 120000.00
          },
          {
              "id": 2,
              "firstName": "jon",
              "lastName": "boe",
              "emailAddress": "jon.boe@mail.com",
              "title": "Engineer",
              "salary": 110000.00
          }
      ]
      ```

  - Find Employee by ID

    - **URL:** https://localhost:8080/v1/employees/1

    - **Method:** GET

    - **Response Status:** 200 OK

    - **Response Body:**
    
      ```json
      {
          "id": 1,
          "firstName": "bob",
          "lastName": "doe",
          "emailAddress": "bob.doe@mail.com",
          "title": "manager",
      	"salary": 120000.00
      }
      ```

---

### Environment Configuration

#### TLS

For security purposes, the RSA key is not included with version control. Instead, developers must create and manage their own key file. It is recommended to place this key within the *src/main/resources/certs* directory, as Git will ignore this by default.

- Navigate to the project directory and create a directory to hold a self signed certificate

  ```
  >> mkdir src/main/resources/certs && cd src/main/resources/certs
  ```

- Create a self signed certificate

  ```
  >> keytool -genkeypair -alias app-name -keyalg RSA -keysize 4096 -storetype
  PKCS12 -keystore app-name.p12 -validity 3650 -storepass changeit
  ```

- Register the certificate locally

  ```
  >> keytool -export -keystore app-name.p12 -alias app-name -file app-name.crt
  ```

  - Enter the keystore password, *changeit*

- Open another terminal instance as an administrator, and import the self signed certificate

  ```
  >> keytool -importcert -file app-name.crt -alias app-name -keystore "C:\Program Files\Java\jdk-version\lib\security\cacerts"
  ```

  - Make sure correct Java version and location are referenced
  - Enter the Java certificate manager password, which is *changeit* by default



#### Database Connection

- Create a Postgres database named *employee-service*

- Run the following baseline script:

  ```sql
  CREATE TABLE job_titles (
      id SERIAL PRIMARY KEY,
      name VARCHAR(255)
  );
  
  CREATE TABLE employees (
      id SERIAL PRIMARY KEY,
      first_name VARCHAR(255),
      last_name VARCHAR(255),
      email_address VARCHAR(255),
      salary DECIMAL(10, 2),
      job_title_id INT,
      FOREIGN KEY (job_title_id) REFERENCES job_titles(id)
  );
  
  INSERT INTO job_titles (name) VALUES ('Software Engineer');
  INSERT INTO employees (first_name, last_name, email_address, salary, job_title_id) VALUES ('Jon', 'Doe', 'jon.doe@mail.com', 150000.00, 1);
  ```



---

### Create Authorization Server

#### Create the database

- Create a Postgres database named *authorization-db*



#### Download Keycloak to project directory

```
>> mkdir ~/projects/authorization-servier && cd ~/projects/authorization-server
```

- Download from https://www.keycloak.org/downloads and extract



#### Enable TLS

- Navigate to the Keycloak configuration directory and generate a private key

  ```
  >> cd keycloak-21.1.2/keycloak-21.1.2/conf
  
  >> keytool -genkeypair -alias authorization-service -keyalg RSA -keysize 4096 -storetype PKCS12 -keystore server.p12 -validity 3650 -storepass changeit
  ```

  - Enter *localhost* for the first value and leave the rest blank

- Configure the JVM to accept the self signed certificate

  ```
  >> keytool -export -keystore server.p12 -alias authorization-service -file server.crt
  
  >> keytool -importcert -file server.crt -alias authorization-service -keystore "C:\Program Files\Java\jdk-17\lib\security\cacerts"
  ```

  - The first operation requires the keystore password, the second requires the JVM certificate manager password. The second operation also requires admin privileges. 

- Replace the existing configuration file, *conf/keycloak.conf*

  ```
  # Database
  db=postgres
  db-username=postgres
  db-password=changeitdb
  db-url=jdbc:postgresql://localhost:5432/authorization-db
  
  # Health
  health-enabled=true
  
  # HTTPS
  https-port=9880
  https-key-store-file=~/projects/authorization-server/keycloak-21.1.2/keycloak-21.1.2/conf/server.p12
  https-key-store-password=changeit
  hostname-url=https://localhost:9880
  ```



#### Start application

```
>> bin/kc.bat start --https-key-store-file=~/projects/authorization-server/keycloak21.1.2/keycloak21.1.2/conf/server.p12
```



#### Configure the Authorization Server

- Log into the Authorization Server at https://localhost:9880 and set up the initial admin user
- Create a Realm named *Employee-Management-Service*
  - Name of the entire application/system
- Create a client named *employee-service*
  - Name of the user facing application/system
- Add a client role named *app-user*
- Add a Realm role named *employee-service-app-user*
  - Tie to *app-user* role
- Enable auto assignment of the realm role *employee-service-app-user* to the registered user
  - Navigate to the *User Registration* tab and add *employee-service-app-user* to the list of default roles
- Update access token lifespan
  - Navigate to *Realm Settings* and select the *Tokens* tab
  - Update *Access Token Lifespan* to twenty four hours
- Update the refresh token lifespan
  - Navigate to *Realm Settings* and select the *Sessions* tab
  - Update *SSO Session Idle* and *SSO Session Max* to twenty four hours



---

### Startup Arguments

```
-Dkeystore-path="classpath:certs/app-name.p12"
-Dkeystore-password="changeit"
-Dkeystore-type="pkcs12"
-Dkeystore-alias="app-name"
-Ddatabase-username="postgres"
-Ddatabase-password="changeit"
-Dkeycloak-admin-username="auth-admin"
-Dkeycloak-admin-password="changeit"
```

- Supplied as VM options in IntelliJ



```
-Dkeystore-path="classpath:certs/employee-service.p12"
-Dkeystore-password="changeit"
-Dkeystore-type="pkcs12"
-Dkeystore-alias="employee-service"
-Dspring.profiles.active="it"
-Dkeycloak-admin-username="NA"
-Dkeycloak-admin-password="NA"
```

- For running integration tests
