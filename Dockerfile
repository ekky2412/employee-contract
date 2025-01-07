# Gunakan image JDK sebagai base image
FROM openjdk:17-jdk-slim

# Set working directory di dalam container
WORKDIR /app

# Salin file JAR dari target ke dalam container
COPY target/EmployeeContract-0.0.1-SNAPSHOT.jar /app/my-app.jar
COPY .env /app/.env

# Expose port aplikasi
EXPOSE 8080

# Perintah untuk menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "my-app.jar"]
