# Etapa de build
FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -Dmaven.compiler.source=21 -Dmaven.compiler.target=21

# Etapa de execução
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /app/target/monitor-cotacoes-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
