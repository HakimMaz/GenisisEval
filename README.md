# GenisisEval

# Building the Project

To build the project, follow these steps:

1. Clone the repository to your local machine.
2. Navigate to the project root directory.
3. Run the following command: `./gradlew build`

This will build the project and create a war file in the `build/libs` directory.

# Running the Project

To run the project, follow these steps:

1. Navigate to the project root directory.
2. Run the following command: `./gradlew bootRun` or if you use Intellij IDE , you can run it from the interface 
or run the main class springbootapplication


This will start the application server and make the API available at `http://localhost:8080`.


# Starting Swagger

To start Swagger, navigate to `http://localhost:8080/swagger-ui/index.html` in your web browser.


# Testing the API using Postman

To test the API using Postman, follow these steps:


1. navigate to the project , download the file CompanyContact_Collection.json
2. import the file in postman
3. now you have all the endpoints and each endpoint. with the necessary data
4. Enjoying chaninging data and test the endpoints 
5. View the response in the "Response" tab.

For example, to retrieve a list of all contact, you would send a GET request to `http://localhost:8080/api/v1/contacts/all`.


