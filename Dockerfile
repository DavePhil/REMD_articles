FROM openjdk:17-alpine
WORKDIR /app

# Install Spring Boot buildpacks
RUN apk add spring-boot-buildpacks

# Build application
RUN spring-boot:build-image --build-arg JAR_FILE=articles.jar

# Expose port
EXPOSE 9001

# Run application
CMD ["java", "-jar", "articles.jar"]