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
Feature: Account Management
  Scenario Outline: Successful login with valid credentials
    Given I am on the JPetStore login page
    When I enter valid username "<username>" and password "<password>"
    Then I should be redirected to the home page
    And I should see my account information

    Examples:
      | username | password |
      | j2ee     | j2ee     |
      | quality1 | 12345678 |
      | quality2 | 12345678 |

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the JPetStore login page
    When I enter invalid username "<username>" and password "<password>"
    Then I should see an error message "Invalid username or password. Sign in failed."

  Scenario: Sign out and verify session
    Given I am logged in to JPetStore with username "j2ee" and password "j2ee"
    When I sign out
    And I press the back button
    Then I should not have access to account services

  Scenario: Change password successfully
    Given I am logged in to JPetStore with username "j2ee" and password "j2ee"
    When I change my password to "newpassword123"
    Then I should see a confirmation message "Your password has been updated."
    And I should be able to log in with the new password "newpassword123"

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