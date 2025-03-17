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
# Author: utsavg03@gmail.com
# Keywords Summary:
# Feature: User Registration
# Scenario: Various registration cases including valid and invalid inputs.
# Given: User is on the registration page.
# When: User enters different sets of input.
# Then: System validates and either creates the user or shows errors.

@registration
Feature: User Registration
  To access the platform, users must register with valid credentials.

  Background:
    Given I am on the "Sign Up" page
  @mismatched_passwords
  Scenario: Registration with mismatched passwords
    When I enter the following user details:
      | Field        | Value           |
      | Username     | 12345678        |
      | Email        | user@test.com   |
      | Password     | Pass1234        |
      | Confirm Pass | Pass5678        |
      | First Name   | John            |
      | Last Name    | Doe             |
      | Phone        | 1234567890      |
      | Address 1    | 123 Main St     |
      | City       	 | Calgary         |
      | State        | AB              |
      | Zip          | 12345           |
      | Country      | Canada          |
    And I click the "Save Account Information" button
    Then I should receive an error message "Passwords do not match"
    And no user should be created

  @empty_fields
  Scenario: Registration with all fields empty
    When I click the "Save Account Information" button without entering any details
    Then I should receive an error message "All fields are mandatory"
    And no user should be created

  @missing_first_name
  Scenario: Registration with missing first name
    When I enter the following user details:
      | Field        | Value           |
      | Username     | 12345678        |
      | Email        | user@test.com   |
      | Password     | SecurePass123   |
      | Confirm Pass | SecurePass123   |
      | First Name   |                 |
      | Last Name    | Doe             |
      | Phone        | 1234567890      |
      | Address 1    | 123 Main St     |
      | City         | Calgary         |
      | State        | AB              |
      | Zip          | 12345           |
      | Country      | Canada          |
    And I click the "Save Account Information" button
    Then I should receive an error message "First name is mandatory"
    And no user should be created

  @missing_individual_fields
  Scenario Outline: Registration with missing required fields
    When I enter my student ID as the username
    And I enter an empty value for "<Field>"
    And I fill all other required fields correctly
    And I click the "Save Account Information" button
    Then I should receive an error message "<Error Message>"
    And no user should be created

    Examples:
      | Field     | Value                      |
      | Last Name | Last name is mandatory            |
      | Email     | Email is mandatory                |
      | Phone     | Phone number is mandatory         |
      | Address 1 | Address 1 is mandatory           |
      | City      | City is mandatory                |
      | State     | State is mandatory               |
      | Zip       | Zip code is mandatory            |
      | Country   | Country is mandatory             |

  @valid_registration_lastname_username
  Scenario: Successful registration with last name as user ID
    When I enter the following user details:
      | Field        | Value            |
      | Username     | Doe              |
      | Email        | user@test.com    |
      | Password     | SecurePass123    |
      | Confirm Pass | SecurePass123    |
      | First Name   | John             |
      | Last Name    | Doe              |
      | Phone        | 1234567890       |
      | Address 1    | 123 Main St      |
      | City       	 | Calgary          |
      | State        | AB               |
      | Zip         | 12345             |
      | Country     | Canada            |
    And I click the "Save Account Information" button
    Then the system should create a new account
    And I should be able to log in with my new account

  @valid_registration_empty_address2
  Scenario: Successful registration with address 2 empty
    When I enter the following user details:
      | Field        | Value            |
      | Username     | 12345678         |
      | Email        | user@test.com    |
      | Password     | SecurePass123    |
      | Confirm Pass | SecurePass123    |
      | First Name   | John             |
      | Last Name    | Doe              |
      | Phone        | 1234567890       |
      | Address 1    | 123 Main St      |
      | Address 2    |                  |
      | City       	 | Calgary          |
      | State        | AB               |
      | Zip         | 12345             |
      | Country     | Canada            |
    And I click the "Save Account Information" button
    Then the system should create a new account
    And I should be able to log in with my new account

  @duplicate_userid
  Scenario: Registration with an existing user ID
    Given an existing user ID "existinguser"
    When I enter the following user details:
      | Field        | Value             |
      | Username     | existinguser      |
      | Email        | user@test.com     |
      | Password     | SecurePass123     |
      | Confirm Pass | SecurePass123     |
      | First Name   | John              |
      | Last Name    | Doe               |
      | Phone        | 1234567890        |
      | Address 1    | 123 Main St       |
      | City       	 | Calgary           |
      | State        | AB                |
      | Zip         | 12345              |
      | Country     | Canada             |
    And I click the "Save Account Information" button
    Then I should receive an error message "User ID already exists"
    And no user should be created

  @profile_category
  Scenario: Successful registration with a selected profile category
    When I enter the following user details:
      | Field        | Value              |
      | Username     | user123            |
      | Email        | user@test.com      |
      | Password     | SecurePass123      |
      | Confirm Pass | SecurePass123      |
      | First Name   | John               |
      | Last Name    | Doe                |
      | Phone        | 1234567890         |
      | Address 1    | 123 Main St        |
      | City       	 | Calgary            |
      | State        | AB                 |
      | Zip         | 12345               |
      | Country     | Canada              |
    And I select "Sports" as my favorite category
    And I click the "Save Account Information" button
    Then the system should create a new account
    And I should be able to log in with my new account
    And the footer image should match the "Sports" category
   @password_complexity
Scenario: Registration with invalid password complexity
  When I enter the following user details:
    | Field        | Value           |
    | Username     | user123         |
    | Email        | user@test.com   |
    | Password     | pass            |  # Too short
    | Confirm Pass | pass            |
    | First Name   | John            |
    | Last Name    | Doe             |
    | Phone        | 1234567890      |
    | Address 1    | 123 Main St     |
    | City       	 | Calgary         |
    | State        | AB              |
    | Zip          | 12345           |
    | Country      | Canada          |
  And I click the "Save Account Information" button
  Then I should receive an error message "Password must be at least 8 characters long"
  And no user should be created 
  @duplicate_email
  Scenario: Registration with an existing email
  Given an existing email "existing@test.com"
  When I enter the following user details:
    | Field        | Value           |
    | Username     | user123         |
    | Email        | existing@test.com |
    | Password     | SecurePass123   |
    | Confirm Pass | SecurePass123   |
    | First Name   | John            |
    | Last Name    | Doe             |
    | Phone        | 1234567890      |
    | Address 1    | 123 Main St     |
    | City       	 | Calgary         |
    | State        | AB              |
    | Zip          | 12345           |
    | Country      | Canada          |
  And I click the "Save Account Information" button
  Then I should receive an error message "Email already exists"
  And no user should be created

  @duplicate_email
  Scenario: Registration with an already existing email
    Given a user with the email "existinguser@test.com" already exists
    When I enter the following user details:
      | Field        | Value                  |
      | Username     | newuser                |
      | Email        | existinguser@test.com  |
      | Password     | SecurePass123          |
      | Confirm Pass | SecurePass123          |
    And I click the "Sign Up" button
    Then I should see an error message "Email already in use"
    And the account should not be created

  @weak_password
  Scenario: Registration with a weak password
    When I enter the following user details:
      | Field        | Value        |
      | Username     | newuser      |
      | Email        | newuser@test.com |
      | Password     | 12345        |
      | Confirm Pass | 12345        |
    And I click the "Sign Up" button
    Then I should see an error message "Password is too weak"
    And the account should not be created

