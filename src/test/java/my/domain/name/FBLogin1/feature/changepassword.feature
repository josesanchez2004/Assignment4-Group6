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
Feature: Change Password
  Scenario: Change password successfully
    Given I am on the JPetStore login page 
    And I am logged in to JPetStore with username "j2ee" and password "j2ee"
    When I change my password to "newpassword123"
    Then I should see a confirmation message "Your password has been updated."
    And I should be able to log in with the new password "newpassword123"
    

  Scenario: Attempting to change password with incorrect repeat password 
  	Given I am on the JPetStore login page 
    And I am logged in to JPetStore with username "j2ee" and password "j2ee"
  	When I attempt to change my password by entering two mismatching values 
  	Then I should see an error message "Passwords do not match"
  

  Scenario: Attempting to change password with a weak password
  	Given I am on the JPetStore login page 
    And I am logged in to JPetStore with username "j2ee" and password "j2ee" 
  	When I change my password to "123"
  	Then I should see an error message "Password must be at least 8 characters long"
  	
  Scenario: Attempting to change password to a previously used password
  	Given I am on the JPetStore login page 
    And I am logged in to JPetStore with username "j2ee" and password "j2ee" 
  	When I change my password to a previously used password
  	Then I should see an error message "Please use a password that has not been previously used" 
  	
  