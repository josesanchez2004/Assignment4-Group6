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
Feature: Login Page
  Scenario Outline: Successful login with valid credentials
    Given I am on the JPetStore login page
    When I enter valid username "<username>" and password "<password>"
    Then I should be redirected to the home page

    Examples:
      | username | password |
      | quality1 | 12345678 |

  Scenario: Unsuccessful login with invalid credentials
    Given I am on the JPetStore login page
    When I enter invalid username "<username>" and password "<password>"
    Then I should see an error message Invalid username or password. Sign in failed.
    
    Examples:
    	| username | password |
    	| quality  | password | 

  Scenario: Sign out and verify session
    Given I am on the JPetStore login page
    And I am logged in to JPetStore with an account
    When I sign out
    Then I should not have access to account services
    
  Scenario: Unsuccessful login with empty credentials
  	Given I am on the JPetStore login page 
  	When I enter an empty username and password 
  	Then I should see an error message Invalid username or password. Sign in failed.
  	
 	Scenario: Unsuccessful login due to case-sensitive username or password
 		Given I am on the JPetStore login page 
 		When I enter username J2EE and password J2EE 
 		Then I should see an error message Invalid username or password
 		