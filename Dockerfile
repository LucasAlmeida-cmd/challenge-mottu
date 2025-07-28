# 1 - Imagem base com Maven e Java
FROM maven:3.9.9-eclipse-temurin-17-alpine

# 2 - Atualiza o repositório do Alpine
RUN apk update && apk add --no-cache bash

# 3 - Cria um novo usuário
RUN adduser -D userapp

# 4 - Define que os próximos comandos usarão esse usuário
USER userapp

# 5 - Define diretório padrão
WORKDIR /app

# 6 - Copia os arquivos do projeto para dentro da imagem
COPY --chown=userapp:userapp . .

# 7 - Compila o projeto com Maven (sem executar testes)
RUN mvn clean package -DskipTests

# Troca de volta para root para copiar o .jar para outra imagem mais leve
USER root

# 8 + 9 - Nova imagem mais enxuta apenas para execução
FROM openjdk:17-jdk-slim

# Cria usuário também na imagem de execução
RUN adduser --disabled-password --gecos "" userapp

USER userapp

WORKDIR /app

# Copia o .jar gerado na imagem anterior
COPY --from=0 /app/target/challenge-mottu-0.0.1-SNAPSHOT.jar app.jar

# 8 - Expondo a porta
EXPOSE 8080

# 9 - Executa o comando com CMD
CMD ["java", "-jar", "app.jar"]
