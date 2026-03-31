FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests -Dspring.datasource.url=jdbc:h2:mem:testdb

CMD ["sh", "-c", "java -jar target/*.jar"]
