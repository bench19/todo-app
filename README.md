Todo backend app implementation for https://www.todobackend.com/ using below technology stack -

	- Spring Boot 2.7
	- Java 1.8
	- Maven 3.8 as build tool
	- Swagger 3.0 for API documentation
	- lombok
	- h2 in-memory database for persistence
	- JUnit and Mockito for testing
	
There are 6 end-points for Todo-app, those are -

	- get Todo item based on Id
	- get all Todo items
	- insert/add/create new Todo item
	- update existing Todo item
	- delete Todo item based on Id
	- delete all Todo items i.e.; clear
	
Contains individual JUnit test cases for -

	- Controller layer
	- Service layer
	 
Added Jacoco Maven plugin for code coverage of implementation layers

	- positive & negative/exception scenario