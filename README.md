# Betty-Boutique
The Betty Boutique repository houses a boutique store web application. This application allows two user types: Users and Admin. Both user type functionalities include browsing the store’s clothing selections, adding items to the shopping cart, checking out the shopping cart, and viewing user information and past history. Only Admin users have the functionality to create new users and products, as well as edit existing users and products.

This app is intended as a capstone project for PNC TEKsystems 2023 Java Full-Stack Development training course (2023-RTT-45-JAVA).

## Directory Description

### src/main/com.teksystems: Contains the main application packages

config 
  - Holds the security configuration for authenticating of a user before accessing the user, product, and cart directories
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/config

controller
  - Holds the controller classes responsible for accepting the user input, performing a task, and returning a response to the specified JSP page
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/controller


database: contains the dao and entity packages - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/database

  dao
    - holds the data abstraction object responsible for making sql queries to the database in relation to a single entity/object
    - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/database/dao
    
  entity 
    - holds the entity models with mapping to their corresponding database table and column
    - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/database/entity
    
formbeans
  - holds the attributes of the input used in the application, along requirements to be considered valid
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/formbeans

authentication
  - holds the authentication implementation and service for a user (new or pre-existing)
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/security

validation
  - holds the validation implementation and interface for determining whether an email is unique to our database
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/java/com/teksystems/validation

### src/main/webapp: Contains the directories containing the application JSP pages, Images, CSS styliings, and Images
pub
  - holds the css, images, and js directories responsible for the application page styling, images, and javascript functionalities
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/webapp/pub

WEB-INF
  - holds the app JSP pages for the application
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/main/webapp/WEB-INF/jsp

### src/test/java/com.teksystems.database.dao: Contains the application unit testing
  - holds the unit test for the UserDAO
  - https://github.com/tug22941/Betty-Boutique/tree/main/src/test/java/com/teksystems/database/dao

### pom.xml
  - contains the dependencies, and plugins employed in this application
  - https://github.com/tug22941/Betty-Boutique/blob/main/pom.xml



## Functionality
  - This application was developed inside the IntelliJ IDE. I used mySQL server for my database. Hibernate is used as the JPA implementation, with springboot being employed for abstraction. In the frontend we used simple HTML, CSS, and JS, with bootstrap for formatting and webpage responsiveness.


## Web Pages

### Index (Landing)
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/index.PNG?raw=true)

### Sign In
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/sign-in.PNG?raw=true)

### Sign Up
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/sign-up.PNG?raw=true)

### Product Search
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/product-search.PNG?raw=true)

### Product Detail
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/product-detail.PNG?raw=true)

### Shopping Cart (Cart Items)
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/cart-1.PNG?raw=true)

### Shopping Cart (Order Information)
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/cart-2.PNG?raw=true)

### Account Information & History
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/account.PNG?raw=true)

### Create User
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/create-user.PNG?raw=true)

### Edit User
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/edit-user.PNG?raw=true)

### User Detail
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/user-detail.PNG?raw=true)

### Create Product
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/create-product.PNG?raw=true)

### Edit Product
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/edit-user.PNG?raw=true)

## Entity Relationship Diagram
![alt text](https://github.com/tug22941/Betty-Boutique/blob/main/gitImg/ERD.PNG?raw=true)
