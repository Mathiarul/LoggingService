#Simple logging service which can be used to log messages to a file or to a database based on the configuration.
#The service can be used to log messages from any application which can send HTTP requests.
#The service can be extended to log messages to any other medium like a message queue or a stream.
#Due to the time constraint using the Log entity in the controller instead of a seperate DTO,that should refactored in the future.
#Just using H2 in memory database for the sake of simplicity, should be replaced with a real database in the future.
