# Flashcards3
[www.flashcards4u.com](http://www.flashcards4u.com) is a test-driven, Java Spring Boot webapp. 
It loads content into flashcards for studying.

## Development

### Dependencies
* Java 18
* Maven 3
* Spring Boot 2.6.7

### Run
* From command line: `./mvnw spring-boot:run`
* Navigate to `http://localhost:8080/` in browser

### Testing
* Run test suite: `./mvnw test`

### Troubleshooting
* See [springboot4 repo](https://github.com/chrisbrickey/springboot4) for sample code and notes

## Content Sources

### CSV Files
This app parses csv files on the server and pushes the content to the browser via html. The combination of these
technologies prevents some characters that are desired in browser (e.g. `,`) from being present in the csv file.

This app performs the following mutations:
* backtick -> comma