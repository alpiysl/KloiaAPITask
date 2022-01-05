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
