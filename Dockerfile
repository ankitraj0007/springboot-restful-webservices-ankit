FROM eclipse-temurin:17

LABEL mentainer = "learnwithankit@gmail.com"

WORKDIR /app

COPY target/springboot-restful-webservices-ankit-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices-ankit.jar

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices-ankit.jar"]