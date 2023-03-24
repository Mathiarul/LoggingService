# Simple Logging Service

This is a simple logging service that allows you to log messages to either a file or a database depending on the configuration. 
The service can be used to log messages from any application that can send HTTP requests. 

## Requirements

* Java 11 or higher
* Maven

## Setup

1. Clone the repository
2. Run the command `mvn clean package` to build the project
3. Run the command `java -jar target/logging-service-0.0.1-SNAPSHOT.jar` to start the server.


## Future Improvements

* Refactor the logging endpoint to use a separate DTO instead of the `Log` entity in the controller.
* Replace the in-memory database with a more robust and scalable database.
* Add support for logging to other mediums like a message queue or a stream.
