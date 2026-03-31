FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . .

# 🔥 ADD THIS LINE (IMPORTANT)
RUN chmod +x mvnw

# build project
RUN ./mvnw clean package -DskipTests -Dspring.datasource.url=jdbc:h2:mem:testdb

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
