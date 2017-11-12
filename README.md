# BulletProofCustomerOrderProcessingApplication

Project Title : BulletProof Customer Order Processing Application

These projects has 3 application. All the application has been built using the spring boot application hence it can improve the performance via installing in multiple docker instances. Also used spring boot batch framework, paginated restful framework for improving the performance.
All applications can be invoked via restful framework links.

Application 1 : BulletProoCSVFirst  : This application has s logic of parsing the csv file and conver it into customer data object and post to the application 2 via JSON Post using RestTemplate post mechanism. This has only one endpoint CSVReaderController. For generating unique id using UID feature of Java this will generate depends on different parameter like System IP, timestamp etc....
This application is configured to run on port 8082

Application 2 : BulletProofCustomerService : This application exposed multiple resources to consume basic CRUD operations for the customer and order data.
This has only one endpoint CustomerOrderEndpoint and it has multiple resources exposed.
This application is configured to run on port 8081

Application 3 : BulletProofCSVProcessor : This uses Spring boot batch framework , resfult paginated framework for improving the performance of the applications.
This has 2 endpoints CustomerJobInvoker and PaginatedCustomerService. CustomerJobInvoker for just scheduling the job and if you hit the url it will start processing the csv processing batch by batch(chunk by chunk) rather processing entire file in one time. Paginated Endpoint can reduce the time for fetching the record in multiple times rather than hitting in one time.
This application is configured to run on port 8080.

Prerequisites

JAVA8, Spring Boot Tool Suite, Browser or Google Chrome Postman App


Installing

Install Java 8
Install Spring Tool Suite
Maven
Import the github project into Tool Suite
Right click on each applications(3 applications) class files via searching *Application.java and run as java application. Each is configured to run on different port hence it will not get conflicted if you are running on single machine.
Customer_Order_Table.sql script has been kept it in the project but I used Spring Data JPA in-memory h2 database for running the application.


Running the tests

To run the application just hit the sample urls

Application 1 Urls for Testing

http://localhost:8082/processCSV   : This will parse csv and post to the application 2.


Application 2 Urls for Testing

http://localhost:8081/customerService/fetchCustomers       : THis will fetch all the customers
http://localhost:8081/customerService/findByName  : This will query based on the customer name
http://localhost:8081/customerService/createOrder/9aaae7c5-6f67-4d2c-93a8-ddf96508e738   This will create order for the mentioned customer id. Please mention the order name in the Postman Requst Body parameter.

Application 3 Urls for testing

http://localhost:8080/invokeCustomerjob
http://localhost:8080/fetchCustomerPagination?size=1


Deployment

Built With

Maven - Dependency Management

Versioning

Github

Author

Sinto C Jose