FROM openjdk:17-slim

WORKDIR /app

RUN apt-get update && apt-get install -y ca-certificates && rm -rf /var/lib/apt/lists/*

COPY target/employee-service-1.0-SNAPSHOT.jar /app
COPY src/main/resources/certs/employee-service.p12 /app
COPY src/main/resources/certs/server.crt /app/server.crt

USER root
RUN chmod 777 /app/employee-service.p12
RUN chmod 777 /app/server.crt
RUN keytool -importcert -file /app/server.crt -alias keycloak-server -cacerts -storepass changeit -noprompt -v
USER 1000

EXPOSE 8080

CMD ["java", "-Dkeystore-path=/app/employee-service.p12", "-Dkeystore-password=changeit", "-Dkeystore-type=pkcs12", "-Dkeystore-alias=employee-service", "-Ddatabase-url=jdbc:postgresql://employee-db:5432/employee-service", "-Ddatabase-username=postgres", "-Ddatabase-password=changeitdb", "-Dkeycloak-admin-username=auth-admin", "-Dkeycloak-admin-password=changeit", "-jar", "employee-service-1.0-SNAPSHOT.jar"]
