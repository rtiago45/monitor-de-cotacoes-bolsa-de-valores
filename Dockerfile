# Etapa de build
FROM eclipse-temurin:21 AS builder
WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Dá permissão para o wrapper do Maven (importante no Linux)
RUN chmod +x ./mvnw

# Compila o projeto com Java 21
RUN ./mvnw clean package -DskipTests -Dmaven.compiler.source=21 -Dmaven.compiler.target=21

# Etapa de execução
FROM eclipse-temurin:21
WORKDIR /app

# Adicione a dependência do socket factory
COPY --from=builder /app/.mvn .mvn
COPY --from=builder /app/pom.xml pom.xml
COPY --from=builder /app/mvnw mvnw

# Copia o .jar da etapa de build
COPY --from=builder /app/target/monitor-cotacoes-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que o Cloud Run usa
EXPOSE 8080

# Inicia a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
