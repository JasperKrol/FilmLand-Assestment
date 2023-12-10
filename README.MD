# Getting Started with Spring Boot Java Application

## Prerequisites
- [Java](https://www.oracle.com/java/) installed
- [Maven](https://maven.apache.org/) installed
- UML in resources

## Steps

### 1. Clone the Repository

### 2. Build the Project
* mvn clean install
### 3. Run the application
### 4. Access the Application
Open your web browser and go to http://localhost:8080.

* H2 console  http://localhost:8080/h2-console 

````
JDBC URL = jdbc:h2:mem:filmland
username = admin
password = password
````

### 5. Login
Use the following credentials to log in:

Email: info@filmland-assessment.nl
Password: Javaiscool90

### 6. Configure the cronjob as you prefer (default is first of the month)
For testing purposes this can be set to every minute, cron expression: 0 */1 * * * *

### 7. Endpoints of this application


GET

GetCategories
http://localhost:8080/api/category/info@filmland-assessment.nl


POST

subscribe
http://localhost:8080/api/subscription/subscribe

Body
````
{
"email": "info@filmland-assessment.nl",
"availableCategory": "Dutch Movies"
} 
````

POST

sharesubscription
http://localhost:8080/api/subscription/shareSubscription


Body

`````
{
"email": "info@filmland-assessment.nl",
"customer": "client@filmland-assessment.nl",
"subscribedCategory": "International Movies"
}
`````

POST

login
http://localhost:8080/api/login

Body
````
{
"email": "info@filmland-assessment.nl",
"password": "Javaiscool90"
}
````
