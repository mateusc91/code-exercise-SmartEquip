

# Sum Service API
This project implements a simple stateless Spring Boot application that exposes two endpoints for adding numbers. The main purpose of this service is to ensure that the users interacting with the application are humans by asking them to solve a simple sum question. The service is designed to be stateless and respond correctly to the client-server interaction.

# Objective
The goal is to simulate a client-server interaction where the client requests a sum question from the server and then submits an answer. The server validates the answer and responds accordingly:

If the sum is correct, the server returns a 200 OK response.
If the sum is incorrect or the request is invalid, the server returns a 400 Bad Request response.

# Requirements
- Java version: 17
- Spring Boot version: 3.3
- Build tool: Maven
- Test Framework: JUnit 5, Spring Boot Test