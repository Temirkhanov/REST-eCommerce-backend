# E-Commerce with Admin Panel Microservice

A secure microservice with CRUD operations for Users, Products, and Transactions built using Spring Boot. This eCommerce app gives you complete control over your online store and lets you easily manage and see data from the database from admin dashboard.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

* MySQL [download](dev.mysql.com/downloads/mysql/)
* Java 8 [download](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Gradle ```$ sdk install gradle 6.0.1 ```
* Postman [download](https://www.getpostman.com/downloads/)  - Testing purposes

### Build
Open terminal and go to project folder: 
```
$ cd eCommerce-backend
```
Build it with gradle:
```
$ gradlew bootJar
$ gradlew bootRun
```

## API Endpoints

#### User

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/api/user/registration`  						| POST |
|`http://localhost:8080/api/user/login`  | GET |
|`http://localhost:8080/api/user/purchase`    	| POST |
|`http://localhost:8080/api/user/products`      	| GET |

#### Admin

|  URL |  Method |
|----------|--------------|
|`http://localhost:8080/api/admin/user-update`  						| PUT |
|`http://localhost:8080/api/admin/user-delete`  | POST |
|`http://localhost:8080/api/admin/user-all`    	| GET |
|`http://localhost:8080/api/admin/user-number`      	| GET |
|`http://localhost:8080/api/admin/product-create`  | POST |
|`http://localhost:8080/api/admin/product-update`    	| PUT |
|`http://localhost:8080/api/admin/product-delete`      	| POST |
|`http://localhost:8080/api/admin/product-all`    	| GET |
|`http://localhost:8080/api/admin/product-number`    	| GET |
|`http://localhost:8080/api/admin/transaction-all`    	| GET |
|`http://localhost:8080/api/admin/transaction-number`    	| GET |


## Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [MySQL](https://dev.mysql.com/doc/)
* [Hibernate](https://hibernate.org/orm/documentation/5.4/)
* [Liquibase](http://www.liquibase.org/documentation/)

## Contributing

Contributions are welcomed!

## Authors

* **Ruslan Temirkhanov** - *Initial work* - [GitHub](https://github.com/Temirkhanov)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
