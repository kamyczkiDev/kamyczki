# Używamy obrazu JDK do budowy
FROM eclipse-temurin:21-jdk-jammy AS builder

# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy plik pom.xml i inne pliki konfiguracyjne Mavena
COPY ./microservices/auth/pom.xml ./
COPY ./microservices/auth/mvnw ./
COPY ./microservices/auth/.mvn .mvn
COPY ./libraries/commons ./commons

# Budujemy commons
RUN ./mvnw clean install -f ./commons/pom.xml

# Kopiujemy cały projekt
COPY ./microservices/auth/src ./src

# Kompilujemy projekt Spring Boot
RUN ./mvnw clean install -DskipTests

# Tworzymy obraz runtime na podstawie Javy
FROM eclipse-temurin:21-jre-jammy

# Ustawiamy katalog roboczy
WORKDIR /app

# Kopiujemy skompilowany plik JAR z poprzedniej fazy
COPY --from=builder /app/target/*.jar app.jar

# Ustawiamy port, na którym aplikacja będzie nasłuchiwać (domyślnie 8080)
EXPOSE 8081

# Definiujemy komendę startową
ENTRYPOINT ["java", "-jar", "app.jar"]
