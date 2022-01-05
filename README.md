#This project used for Kloia Qa API Task

##Rules:
● BaseURI: https://petstore.swagger.io/v2  
● Swagger Documentation: https://petstore.swagger.io  
● The project should be shared in a git repository  
● Karate Framework should be used for this case study  
● Each execution should generate a HTML report  
● README.md file should be added  
● Project can be run on the Command line    

`Tasks`  
Task1  
-Set path pet/findByStatus  
-Use query-string parameters to set the rest of the url path  
-Get pets for the given statuses below (Scenario Outline scheme should be used)  
○ Available  
○ Pending  
● Verify the below requested in the response:  
○ Status code should be 200  
○ Content-type should be application/json  
○ status should be equal to the posted status  
○ Each item in response should have an id and not null  

Task2  
-Develop a helper JavaScript/Java function in a helper directory to create email  

Task3  
-Set the Content-type=application/json  
● Set path user  
● Create a user with the following JSON model  
○ Each field in the JSON model should be randomly generated for each call  
○ The method which is created with Task-2 SHOULD be used to set email  
○ Helper methods like email generation can be developed and used for the  
other attributes in the JSON model  
{  
"id": 0,  
"username": "string",  
"firstName": "string",  
"lastName": "string",  
"email": "string",  
"password": "string",  
"phone": "string",  
"userStatus":  
}  
● Verify the below requested in the response:  
○ Status code should be 200  
○ message should be equal to the posted id  

Task4  
Create a scenario by copying Task-3  
● Modify the scenario to take username and lastname as parameter  
● Rest of the JSON field can be generated inside the scenario randomly  
● Call this scenario in other scenario by giving your name and last name as parameter  

`resolve conflicts`
- git add . (stage changes)
- git commit -m "message" (commit)
- git push (and push it to github)
- open pull request

`get report`
- mvn clean verify

`class`
- FeatureRunner Class runs the tests with tag
```java
@RunWith(Karate.class)
@KarateOptions(
//        tags = "@task4"
)
public class FeatureRunner {
    }
}
```

-EmailGenerator Class generates email with Faker class
```java
public class EmailGenerator {

    public static String generateEmail(){
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }
}
```
-UserGenerator Class generates new user with Faker class
```java
public class UserGenerator {

    public static Map<String, Object> user;

    public static Map<String, Object> generateUser() {
        Faker faker = new Faker();
        user = new HashMap<>();
        user.put("id", faker.number().numberBetween(1,100));
        user.put("username", faker.name().username());
        user.put("firstName", faker.name().firstName());
        user.put("lastName", faker.name().lastName());
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("phone", faker.phoneNumber().cellPhone());
        user.put("userStatus", faker.number().randomDigitNotZero());
        return user;
    }

    public static Map<String, Object> generateUser(String name, String lastName) {
        Faker faker = new Faker();
        user = new HashMap<>();
        user.put("id", faker.number().numberBetween(1,100));
        user.put("username", faker.name().username());
        user.put("firstName", name);
        user.put("lastName", lastName);
        user.put("email", faker.internet().emailAddress());
        user.put("password", faker.internet().password());
        user.put("phone", faker.phoneNumber().cellPhone());
        user.put("userStatus", faker.number().randomDigitNotZero());
        return user;
    }

    public static String idNumber(){
        return String.valueOf(user.get("id"));
    }
}
```

`featues`
-Tasks feature file contains tasks

```gherkin
   Feature: Kloia API Tasks

  Background:
    * def baseUrl = 'https://petstore.swagger.io/v2'

  @task1
  Scenario Outline: Task 1
    Given url baseUrl
    And path '/pet/findByStatus'
    And param status = '<status>'
    And method GET
    Then status 200
    And header Content-Type = 'application/json'
    And match each response[*].status == '<status>'
    And match each response[*].id == '#number'
    Examples:
      | status    |
      | available |
      | pending   |

  @task2
  Scenario: Task 2
    * def generateEmail = Java.type('EmailGenerator').generateEmail()
    * print email

  @task3
  Scenario: Task 3
    * def generateUser = Java.type('UserGenerator').generateUser()
    * def id = Java.type('UserGenerator').idNumber()
    * print id
    * print generateUser
    Given url baseUrl
    And path '/user'
    When request generateUser
    And method POST
    Then status 200
    * print response.message
    And match response.message == id

  @reCall
  Scenario: Task 4 recall
    * def generateUser = Java.type('UserGenerator').generateUser(name,lastName)
    * def id = Java.type('UserGenerator').idNumber()
    * print id
    * print generateUser
    Given url baseUrl
    And path '/user'
    When request generateUser
    And method POST
    Then status 200
    * print response.message
    And match response.message == id

  @task4
  Scenario: Task 4
    * def result = call read('Tasks.feature@reCall') {name : Alpaslan, lastName: Uysal}
```

`step defintions`
