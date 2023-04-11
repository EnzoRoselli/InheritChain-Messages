# **:link: InheritChain-Messages**

InheritChain-Messages is a Java-based microservice responsible for managing text and video messages (only text messages are included in the MVP) within the InheritChain application. It provides a set of API endpoints to handle saving, deleting, and fetching messages from the database, as well as storing heir addresses for each message. The service is used when an owner saves messages with heir addresses to inherit them and when heirs claim their inheritance to display the messages they've inherited.

## **Technologies Used :computer:**

- Java 17
- Spring Boot 3.0.5
- Maven
- MySQL
- Lombok

## **Requirements :clipboard:**

- **[JDK 17](https://jdk.java.net/17/)**
- **[MySQL 8](https://dev.mysql.com/downloads/mysql/)**
- **[Maven](https://maven.apache.org/download.cgi)**
- **[Git](https://git-scm.com/downloads)**

## **Installation and Setup :wrench:**

1. Clone the project
    
    ```
    git clone https://github.com/EnzoRoselli/InheritChain-Messages
    ```
    
2. Change to the project directory
    
    ```
    cd InheritChain-Messages
    ```
    
3. Create an **`application.properties`** file in the **`src/main/resources`** directory. Add the following properties:
    
    ```
    spring.datasource.url=${DB_URL}
    spring.datasource.username=${DB_USERNAME}
    spring.datasource.password=${DB_PASSWORD}
    ```
    
4. Install dependencies and build the project
    
    ```
    mvn clean install
    ```
    
5. Run the application
    
    ```
    mvn spring-boot:run
    ```
    

## **API Endpoints :globe_with_meridians:**

1. Save message
    
    ```
    POST /messages
    ```
    
2. Find message by ID
    
    ```
    GET /messages/{id}
    ```
    
3. Find messages by admin address and inheritance contract address
    
    ```
    GET /messages?adminAddress={adminAddress}&inheritanceContractAddress={inheritanceContractAddress}
    ```
    
4. Add heir addresses to a message
    
    ```
    PUT /messages/{messageId}/heir-addresses
    ```
    
5. Delete message by ID
    
    ```
    DELETE /messages/{id}
    ```
    
6. Get messages by heir address and inheritance contract address
    
    ```
    GET /messages/heir-addresses?heirAddress={heirAddress}&inheritanceContractAddress={inheritanceContractAddress}
    ```
    

## **Accessing the API :computer:**

- Use a REST client, such as Postman, to send requests to the API endpoints.
- You can hit the app using the following url: https://localhost:8080/

## **Contributing :handshake:**

1. Fork the project
2. Create a feature branch using GitFlow
3. Commit your changes
4. Push to the branch
5. Open a pull request

## **Branching (GitFlow) :sparkler:**

- Feature: Local/remote branch for a feature. After merging it into develop, delete it.
- Develop: Stores all completed features that haven’t yet been released.
- Master: Stores the finished releases.

---

By Enzo Roselli
