# Usa una imagen oficial de OpenJDK
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR (ajusta el nombre si es necesario)
COPY target/arai-backend-0.0.1-SNAPSHOT.jar app.jar

# Carpeta para logs (se vincula con el host en el "docker run")
RUN mkdir -p /app/logs

# Puerto que expone la aplicación
EXPOSE 5002

# Punto de entrada (usa "exec" para mejor manejo de señales)
ENTRYPOINT ["java", "-jar", "app.jar"]

# Volumen para logs y archivo de configuración externo
VOLUME ["/app/logs", "/app/.env.properties"]
