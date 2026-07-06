# 1. Usamos una máquina virtual de Java 17 con Maven incluido
FROM maven:3.9.6-eclipse-temurin-17

# 2. Le decimos que trabaje en la carpeta /app
WORKDIR /app

# 3. Copiamos todo tu código a la nube
COPY . .

# 4. Render ejecuta este comando para crear el .jar automáticamente
RUN mvn clean package -DskipTests

# 5. Exponemos el puerto 8080 para internet
EXPOSE 8080

# 6. Render ejecuta el .jar automáticamente sin importar el nombre exacto
CMD ["sh", "-c", "java -jar target/*SNAPSHOT.jar"]