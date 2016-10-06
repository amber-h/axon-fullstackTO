## FullstackTO - simple CQRS app with Axon

 * This is a simple spring boot application, using the Axon framework for Java. 
 * You will need groovy, gradle and mongodb installed to run this application.

### Installing MongoDB
  
 ```brew install mongodb```
 
### Running MongoDB

 ```
 mkdir -p /data/db
 mongod
 ```

### Running the application

`./gradlew bootRun`

Using your rest client of choice, (ex [Postman](https://www.getpostman.com/)) hit the endpoint localhost:8080/orders to open an order
