# Flashcards3
Test-driven, Java Spring Boot webapp with Maven

## Dependencies
* Java 18
* Maven 3
* Spring Boot 2.6.7

## Run
* From command line: `./mvnw spring-boot:run`
* Navigate to `http://localhost:8080/` in browser

## Testing
* Run test suite: `./mvnw test`

## CSV Files
This app parses csv files on the server and pushes the content to the browser via html. The combination of these
technologies prevents some characters that are desired in browser (e.g. `,`) from being present in the csv file.

This app performs the following mutations:
- backtick -> comma

## Troubleshooting
* See [springboot4 repo](https://github.com/chrisbrickey/springboot4) for sample code and notes