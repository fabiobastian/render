# Use uma imagem base com o JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR gerado para dentro do contêiner
COPY target/calcadoapi-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta na qual a aplicação Spring será executada
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
