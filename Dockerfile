# Gunakan image JDK sebagai base image
FROM openjdk:17-jdk-slim

# Set working directory di dalam container
WORKDIR /app

# Salin file JAR dari target ke dalam container
COPY target/your-app.jar /app/your-app.jar

# Expose port aplikasi
EXPOSE 8080

# Perintah untuk menjalankan aplikasi
ENTRYPOINT ["java", "-jar", "your-app.jar"]
