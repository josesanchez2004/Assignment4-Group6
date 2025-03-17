#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
  Scenario: Register a new user
    Given I am on the JPetStore registration page
    When I enter valid user information:
      | Field       | Value            |
      | User ID     | newuser          |
      | Password    | 12345678         |
      | First Name  | John             |
      | Last Name   | Doe              |
      | Email       | john.doe@test.com|
      | Phone       | 1234567890       |
      | Address 1   | 123 Main St      |
      | City        | town          	 |
      | State       | AB               |
      | Zip         | 12345            |
      | Country     | Canada           |
    And I click the "Save Account Information" button
    Then I should be redirected to the home page
    And I should see a welcome message with my username "newuser"