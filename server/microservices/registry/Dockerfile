FROM eclipse-temurin:21-jdk-jammy AS builder
# Ustawiamy katalog roboczy wewnątrz kontenera
WORKDIR /app

# Kopiujemy plik pom.xml i inne pliki konfiguracyjne Mavena
COPY pom.xml ./
COPY mvnw ./
COPY .mvn .mvn

# Pobieramy wszystkie zależności
RUN ./mvnw dependency:go-offline -B

# Kopiujemy cały projekt
COPY src ./src

# Kompilujemy projekt Spring Boot
RUN ./mvnw clean package -DskipTests

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