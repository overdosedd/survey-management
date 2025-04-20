# Survey Management System with OCI Logging

This is a Dropwizard-based Java REST API that receives and logs survey submissions into Oracle Cloud Infrastructure (OCI) Logging.

## How to Run

##  Project Structure
#### SurveyService Application
- `src/main/java/`: Main application source code
    - `api/SurveyResources.java`: Survey REST endpoint
    - `core/SurveyEntity.java`: Data model
    - `oci/LoggingOCIClients.java`: OCI Logging client
- `config.yml`: Dropwizard config
- `Dockerfile`: Dockerized build of the application
- `README.md`: Project documentation

#### SampleGeneration Application
- `src/main/java/`: Main application source code
  - `SampleGenerationApplication.java` : Main Application to run survey generate
  - `/entity/SurveyEntity.java` : Data model for survey generation
---

##  How to Run

###  Prerequisites

- Java 11+
- Maven 3+
- OCI CLI or Environment with OCI credentials configured
- OCI Logging service log ID
- OCI SDK config with access keys or instance principal
- Creation of Oracle cloud account 
- Creation of public and private key to upload to oracle cloud
- Installation of Dockers 

### Installation of Dockers
1. go to https://docs.docker.com/desktop/setup/install/windows-install/
2. install

### Installation of OpenSSL 
1.go to  https://slproweb.com/products/Win32OpenSSL.html
2. install the .exe for openssl 

### Creation of public and private openssl key 
- Run this command in your terminal:

  1. openssl genrsa -out ~/.oci/oci_api_key.pem 2048
  
- Then generate the public key from the private key:
    1. openssl rsa -pubout -in ~/.oci/oci_api_key.pem -out ~/.oci/oci_api_key_public.pem


### Set up ~/.oci/config

1. mkdir -p ~/.oci 
2. create a config file through notepad ++, save as all file
3. sample config file input: 
   
    [DEFAULT]
      user=ocid1.user.oc1..your_user_ocid
      fingerprint=your_fingerprint
      key_file=/path/to/your/private/key
      tenancy=ocid1.tenancy.oc1..your_tenancy_ocid
      region=us-phoenix-1 

   

### Oracle Cloud setup 
1. create new account at https://cloud.oracle.com/
2. Once created go to API keys on the left. 
3. Add api key by uploading the public key created when creation of ~/.oci/config
4. Choose "Upload public key", and upload oci_api_key_public.pem.
5. OCI will:

   1. Register your key.

   2. Display the Fingerprint (you need this for your config file).

   3. Copy the fingerprint now or note it from the API Key table.
6. paste the fingerprint to your~/.oci/config

###  Build the application

### Setting up config.yml 
- logID is to store the oci logid for your logs when creating new custom logs. (format (ocid1.log.oc1.<region>.<your oci code>))
- server is to define the port, configures the HTTP server that Dropwizard uses (backed by Jetty)
- logging configures logging behavior using Dropwizard’s built-in support
- swagger configures the Swagger (OpenAPI) integration

### To Run Locally 
- java -jar target/survey-service.jar server config.yml

### Dependencies Explaination in pom.xml

- DropWizard dependicies to enable usage of drop wizard
  <!-- Dropwizard Core -->
        <dependency>
            <groupId>io.dropwizard</groupId>
            <artifactId>dropwizard-core</artifactId>
            <version>${dropwizard.version}</version>
        </dependency>

- OCI sdk dependencies to connect to oracle cloud 
     <!-- OCI SDK -->
        <dependency>
            <groupId>com.oracle.oci.sdk</groupId>
            <artifactId>oci-java-sdk-loggingingestion</artifactId>
            <version>3.35.0</version>
        </dependency>

        <dependency>
            <groupId>com.oracle.oci.sdk</groupId>
            <artifactId>oci-java-sdk-common-httpclient-jersey</artifactId>
            <version>3.35.0</version>
        </dependency>

-JAVA dependencies for web application

    <dependency>
            <groupId>org.hibernate.validator</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>6.2.0.Final</version>
        </dependency>
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>2.0.1.Final</version>
        </dependency>

        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>compile</scope>
        </dependency>

- Swagger to enable swagger ui

        <dependency>
            <groupId>com.smoketurner</groupId>
            <artifactId>dropwizard-swagger</artifactId>
            <version>2.1.4-1</version>
        </dependency>




### API endpoints 
- POST /surveys
- {
  "surveyID": "abc123",
  "age": 25,
  "gender": "female",
  "region": "APAC",
  "score": 4
  }
### Creation of dockerfile

    FROM container-registry.oracle.com/java/openjdk:21
    # Create working directory in the container
    WORKDIR /app
    # Copy the fat jar into the container (adjust filename if needed)
    COPY target/SurveyServiceApplication-0.0.1-SNAPSHOT.jar app.jar
    # Copy your Dropwizard config YAML (if needed)
    # Expose the default application port
    EXPOSE 8080
    # Start the Dropwizard service 
    CMD ["java", "-jar", "app.jar", "server", "config.yml"]

1. FROM  is to fetch the jdk from where
2. next we will copy the jar and also the dropwizard config into it
3. we will expose the port 
4. list out some of the dropwizard service  

### Run Dockers 
docker build -t survey-service .

### Run Docker containers 
docker run -p 8080:8080 survey-service

### Run SurveyGenerator
1. Trigger run for SurveyGenerator Application
2. Survey Service must be running first

### Swagger url
http://localhost:8080/swagger#/default/receiveSurvey