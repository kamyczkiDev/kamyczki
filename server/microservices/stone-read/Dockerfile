# Używamy obrazu JDK do budowy
FROM eclipse-temurin:21-jdk-jammy AS builder

LABEL org.opencontainers.image.description DESCRIPTION

# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy plik pom.xml i inne pliki konfiguracyjne Mavena
COPY ./microservices/stone-read/pom.xml ./
COPY ./microservices/stone-read/mvnw ./
COPY ./microservices/stone-read/.mvn .mvn
COPY ./libraries/commons ./commons

# Budujemy commons
RUN chmod +x mvnw ./mvnw
RUN ./mvnw clean install -f ./commons/commons-stone/pom.xml

# Kopiujemy cały projekt
COPY ./microservices/stone-read/src ./src

# Kompilujemy projekt Spring Boot
RUN ./mvnw clean install -DskipTests

# Tworzymy obraz runtime na podstawie Javy
FROM eclipse-temurin:21-jre-jammy

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy skompilowany plik JAR z poprzedniej fazy
COPY --from=builder /app/target/*.jar app.jar

# Ustawiamy port, na którym aplikacja będzie nasłuchiwać (domyślnie 8080)
EXPOSE 8082

# Definiujemy komendę startową
ENTRYPOINT ["java", "-jar", "app.jar"]
