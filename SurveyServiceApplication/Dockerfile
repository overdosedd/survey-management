FROM container-registry.oracle.com/java/openjdk:21

# Create working directory in the container
WORKDIR /app

# Copy the fat jar into the container (adjust filename if needed)
COPY target/SurveyServiceApplication-0.0.1-SNAPSHOT.jar app.jar

# Copy your Dropwizard config YAML (if needed)
COPY config.yml config.yml


# Expose the default application port
EXPOSE 8080

# Start the Dropwizard service
CMD ["java", "-jar", "app.jar", "server", "config.yml"]