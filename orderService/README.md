# **Requirements**

For building and running the application you need:

`JDK 1.8`

`Maven 3.6.2`

`mysql-connector-java
create sql schema as per mentioned in application.properties`

# **Running the application locally**
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.example.orderService.OrderServiceApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

    mvn spring-boot:run

# **API specifications**

Check End-points in Postman

a). Add Order

    /order
    method - POST
    
    body -  {
                "deliveryInfo": [{
                    "firstName": "Ankita",
                    "lastName": "Tiwari",
                    "email": "at@gmail.com",
                    "contactNo": "7688679809",
                    "address": "bellandur"
                }],
                "totalCost": 400.00
            }


b). getAll Orders

    /order
    method - GET
    
    

