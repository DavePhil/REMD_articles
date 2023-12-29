FROM openjdk:17-alpine
WORKDIR /app
COPY target/remd_articles.jar /app
CMD ["java","-jar","remd_articles.jar"]