# Flashcards3
[www.flashcards4u.com](http://www.flashcards4u.com) is a test-driven, Java Spring Boot webapp. 
It loads content into flashcards for studying. Click the card to toggle between front and back.
Click the 'next' button to view the subsequent card.

![Screenshot](https://res.cloudinary.com/dckkkjkuz/image/upload/v1658001860/portfolio/flashcards4u-screenshot.png)

## Development

### Dependencies
* Java 21
* Spring Boot 2.7.18
* Maven 3

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