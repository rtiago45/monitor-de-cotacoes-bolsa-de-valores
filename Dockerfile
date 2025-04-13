FROM ubuntu:latest
LABEL authors="Tiagoo"

ENTRYPOINT ["top", "-b"]


# Etapa de build
FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Etapa de execução
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /app/target/monitor-cotacoes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
