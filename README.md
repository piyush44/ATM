# ATM-Application-RestAPIs

# Spring Boot, H2, JPA, Hibernate Rest API 

The app will start running at <http://localhost:8080>.

## Explore Rest APIs

The app defines following CRUD APIs.

    GET http://localhost:8080/users
    
    GET http://localhost:8080/users/
    
    POST http://localhost:8080/users/
    
    PUT http://localhost:8080/users/
    
    GET http://localhost:8080/users/accounts
    
    GET http://localhost:8080/users/accounts?userId=100&accountId=105 

    PUT users/{userId}/accounts/{accountId}/withdraw
    
    PUT users/{userId}/accounts/{accountId}/deposit
    
    POST /users/{userId}/accounts
    
    PUT /users/{userId}/accounts
    
    GET /users/{userId}/accounts/{accountId}/getBalance
    
    GET /users/{userId}/accounts/{accountId}/transactions
    
    GET /users/{userId}/accounts/{accountID}/transactions/{transactionId}
You can test them using postman or any other rest client.
