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

@registration
Feature: User Registration
  To access the platform, users must register with valid credentials.

  Background:
    Given I am on the "Sign Up" page
  @mismatched_passwords
  Scenario: Registration with mismatched passwords
    When I enter mismatching password:
    And I click the "Save Account Information" button
    Then I should receive an error message 

@empty_fields
Scenario: Registration with all fields empty
  When I click the "Save Account Information" button without entering any details
  Then I should get an error message

  @missing_individual_fields
Scenario: Registration with  required fields empty
  When I click the "Save Account Information" button without entering required details
  Then I should get a error message
  
  @duplicateuserid
  Scenario: Registration with an existing user ID
    Given an existing user ID "existinguser"
    When I enter all user details:
    And I click the button
    Then I should receive an error message


  @duplicate_email
  Scenario: Registration with an existing user email
    Given an existing email "existinguser"
    When I enter all user details:
    And I click the button
    Then I should receive an error message
    
    



