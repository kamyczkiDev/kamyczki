FROM eclipse-temurin:21-jdk-jammy AS builder

LABEL org.opencontainers.image.description DESCRIPTION
# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy plik pom.xml i inne pliki konfiguracyjne Mavena
COPY ./microservices/gateway/pom.xml ./
COPY ./microservices/gateway/mvnw ./
COPY ./microservices/gateway/.mvn .mvn
COPY ./libraries/commons ./commons

# Budujemy commons
RUN ./mvnw clean install -f ./commons/pom.xml

# Kopiujemy cały projekt
COPY ./microservices/gateway/src ./src

# Kompilujemy projekt Spring Boot
RUN ./mvnw clean install -DskipTests

# Tworzymy obraz runtime na podstawie Javy
FROM eclipse-temurin:21-jre-jammy

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy skompilowany plik JAR z poprzedniej fazy
COPY --from=builder /app/target/*.jar app.jar

# Ustawiamy port, na którym aplikacja będzie nasłuchiwać (domyślnie 8080)
EXPOSE 8080

# Definiujemy komendę startową
ENTRYPOINT ["java", "-jar", "app.jar"]