#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

####
#FROM openjdk:17-jdk-alpine

# Copia el código del backend
#COPY . /app

# Establece el directorio de trabajo
#WORKDIR /app

# Expone el puerto 8080
#EXPOSE 8080

# Ejecuta el comando para iniciar el backend
#CMD ["java", "-jar", "sumativa1.jar"]


FROM openjdk:17-jdk-slim

# Copia el archivo jar
COPY target/*.jar app.jar

# Copia el wallet de Oracle
COPY Wallet_fullstack3 /app/Wallet_fullstack3

# Establece el directorio de trabajo
WORKDIR /app

# Expone el puerto 8080
EXPOSE 8080

# Ejecuta el comando para iniciar el backend
ENTRYPOINT ["java", "-jar", "/app.jar"]
