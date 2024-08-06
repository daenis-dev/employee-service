# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-oracle

# Configure Postgres
FROM postgres:latest

ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=changeitdb
ENV POSTGRES_DB=employee-service
COPY src/main/resources/schema.sql /docker-entrypoint-initdb.d/

EXPOSE 5432

CMD ["postgres"]

# Set the working directory inside the container
WORKDIR /app

# Copy the application files into the container
COPY target/employee-service-1.0-SNAPSHOT.jar /app
COPY src/main/resources/certs/employee-service.p12 /app
# Below command causing issues - remove from
COPY --from=postgres-run /docker-entrypoint-initdb.d/ /docker-entrypoint-initdb.d/

# Expose the port the database and app run on
EXPOSE 8080

# Specify the command to run on container start (include VM args) (try )
CMD ["java", "-Dkeystore-path=/app/employee-service.p12", "-Dkeystore-password=changeit", "-Dkeystore-type=pkcs12", "-Dkeystore-alias=employee-service", "-Ddatabase-url=jdbc:postgresql://localhost:5432/employee-service", "-Ddatabase-username=postgres", "-Ddatabase-password=changeitdb", "-Dkeycloak-admin-username=auth-admin", "-Dkeycloak-admin-password=changeitauthadmin", "-jar", "employee-service-1.0-SNAPSHOT.jar"]

