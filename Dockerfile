FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/*.jar springboot-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","springboot-app.jar"]