# Używamy obrazu JDK do budowy
FROM eclipse-temurin:21-jdk-jammy AS builder

LABEL org.opencontainers.image.description DESCRIPTION

# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy plik pom.xml i inne pliki konfiguracyjne Mavena
COPY ./microservices/auth/auth-service ./
COPY ./microservices/auth/mvnw ./
COPY ./microservices/auth/.mvn .mvn
COPY ./libraries/commons ./commons
COPY ./microservices/auth/auth-feign ./auth-feign

# Budujemy commons
RUN ./mvnw clean install -f ./commons/pom.xml
RUN ./mvnw clean install -f ./auth-feign/pom.xml

# Kompilujemy projekt Spring Boot
RUN ./mvnw clean install -DskipTests -Dcheckstyle.skip=true -Dpmd.skip=true

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