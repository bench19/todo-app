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
	
Added Java REST API client for testing all APIs using

	- RestTemplate for synchronous calls
	- AsyncRestTemplate for asynchronous calls (deprecated in Spring 5 instead use WebClient)
	- Use WebClient.Builder from Spring 5 version for both synchronous & asynchronous calls
	
Added Spring 5 Webflux for Reactive programming

	- Use Builder pattern to test different REST APIs using WebClient.Builder
	
Added generic/global exception handler 

	- @RestControllerAdvice
	- @ExceptionHandler
	- Extending abstract class ResponseEntityExceptionHandler & overriding/implementing methods