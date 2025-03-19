package my.domain.name.FBLogin1.steps;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {
    static WebDriver driver;

    @Given("I am on the JPetStore login page")
    public void i_am_on_the_j_pet_store_login_page() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/jpetstore/actions/Account.action;?signonForm=");
    }

    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
    	driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);
        
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("signon")).click();
    }
    
    @When("I enter invalid username {string} and password {string}")
    public void i_enter_invalid_username_and_password(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("signon")).click();
    }

    @Then("I should be redirected to the home page")
    public void i_should_be_redirected_to_the_home_page() {
        assertTrue(driver.getCurrentUrl().contains("35.208.95.33:8080/jpetstore/actions/Catalog.action"));
    }

    @Then("I should see my account information")
    public void i_should_see_my_account_information() {
        WebElement accountInfo = driver.findElement(By.id("WelcomeContent"));
        assertTrue(accountInfo.getText().contains("Welcome"));
    }

    @Then("I should see an error message {string}")
    public void i_should_see_an_error_message(String errorMessage) {
        WebElement error = driver.findElement(By.cssSelector(".error"));
        assertTrue(error.getText().contains(errorMessage));
    }
    
    @When("I sign out")
    public void i_should_sign_out() {
    	driver.findElement(By.linkText("Sign Out")).click();
    }
    
    @When("I should not have access to account services")
    public void i_should_not_have_access() {
    	assertTrue(driver.getCurrentUrl().contains("http://35.208.95.33:8080/jpetstore/actions/Catalog.action"));
    }
    @When("I enter an empty username and password")
    public void i_entered_empty_username_and_password() {
        driver.findElement(By.name("username")).sendKeys(" ");
        driver.findElement(By.name("password")).sendKeys(" ");
        driver.findElement(By.name("signon")).click();
    }
    
    @When("I enter username `J2EE` and password `J2EE`")
    public void i_entered_case_sensitive() {
        driver.findElement(By.name("username")).sendKeys("J2EE");
        driver.findElement(By.name("password")).sendKeys("J2EE");
        driver.findElement(By.name("signon")).click();
    }
    
    @Given("I am on the Sign Up page")
    public void i_am_on_the_sign_up_page() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/jpetstore/actions/Account.action?newAccountForm=");
    }

    @When("I enter user details with mismatched passwords")
    public void i_enter_user_details_with_mismatched_passwords() {
        driver.findElement(By.name("username")).sendKeys("12345678");
        driver.findElement(By.name("email")).sendKeys("user@test.com");
        driver.findElement(By.name("password")).sendKeys("Pass1234");
        driver.findElement(By.name("confirmPassword")).sendKeys("Pass5678");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Calgary");
        driver.findElement(By.name("state")).sendKeys("AB");
        driver.findElement(By.name("zip")).sendKeys("12345");
        driver.findElement(By.name("country")).sendKeys("Canada");
        driver.findElement(By.name("saveAccountInfo")).click();
    }

//    @Then("I should see an error message")
//    public void i_should_see_an_error_message() {
//        WebElement error = driver.findElement(By.cssSelector("#Content .messages li"));
//        assertTrue(error.isDisplayed()); 
//    }

    @Then("No user should be created")
    public void no_user_should_be_created() {
        assertTrue(driver.getCurrentUrl().contains("Account.action?newAccountForm"));
    }

    @When("I attempt to register without entering any details")
    public void i_attempt_to_register_without_entering_any_details() {
        driver.findElement(By.name("saveAccountInfo")).click();
    }

//    @Then("I should receive an error message")
//    public void i_should_receive_an_error_message() {
//        WebElement error = driver.findElement(By.className("messages"));
//        assertTrue(error.isDisplayed()); 
//    }

    @When("I enter valid user details")
    public void i_enter_valid_user_details() {
        driver.findElement(By.name("username")).sendKeys("newuser");
        driver.findElement(By.name("email")).sendKeys("newuser@test.com");
        driver.findElement(By.name("password")).sendKeys("SecurePass123");
        driver.findElement(By.name("confirmPassword")).sendKeys("SecurePass123");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Calgary");
        driver.findElement(By.name("state")).sendKeys("AB");
        driver.findElement(By.name("zip")).sendKeys("12345");
        driver.findElement(By.name("country")).sendKeys("Canada");
        driver.findElement(By.name("saveAccountInfo")).click();
    }

    @Then("I should be redirected to the login page")
    public void i_should_be_redirected_to_the_login_page() {
        assertTrue(driver.getCurrentUrl().contains("Account.action?signonForm"));
    }

    @Given("I enter valid user details with a missing {string}")
    public void i_enter_valid_user_details_with_missing_field(String field) {
        driver.findElement(By.name("username")).sendKeys("user123");
        driver.findElement(By.name("email")).sendKeys("user123@test.com");
        driver.findElement(By.name("password")).sendKeys("SecurePass123");
        driver.findElement(By.name("confirmPassword")).sendKeys("SecurePass123");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Calgary");
        driver.findElement(By.name("state")).sendKeys("AB");
        driver.findElement(By.name("zip")).sendKeys("12345");
        driver.findElement(By.name("country")).sendKeys("Canada");

        if ("lastName".equals(field)) {
            driver.findElement(By.name("lastName")).clear(); 
        }
    }

    @Then("I should see an error message for missing field")
    public void i_should_see_an_error_message_for_missing_field() {
        WebElement error = driver.findElement(By.cssSelector("#Content .messages li"));
        assertTrue(error.isDisplayed()); 
    }

   
    @When("I enter a duplicate email")
    public void i_enter_a_duplicate_email() {
        driver.findElement(By.name("email")).sendKeys("existinguser@test.com");
        driver.findElement(By.name("username")).sendKeys("newuser");
        driver.findElement(By.name("password")).sendKeys("SecurePass123");
        driver.findElement(By.name("confirmPassword")).sendKeys("SecurePass123");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Calgary");
        driver.findElement(By.name("state")).sendKeys("AB");
        driver.findElement(By.name("zip")).sendKeys("12345");
        driver.findElement(By.name("country")).sendKeys("Canada");
        driver.findElement(By.name("saveAccountInfo")).click();
    }

    @Then("I should see an error message for duplicate email")
    public void i_should_see_an_error_message_for_duplicate_email() {
        WebElement error = driver.findElement(By.cssSelector("#Content .messages li"));
        assertTrue(error.isDisplayed());
    }
    
    @When("I change my password to newpassword123")
    public void change_password_to_different(){
    	driver.findElement(By.linkText("My Account")).click();
		
		driver.findElement(By.name("password")).sendKeys("newpassword123");
        driver.findElement(By.name("repeatedPassword")).sendKeys("newpassword123");
        driver.findElement(By.name("editAccount")).click();
    }
    
	@Then("I should be able to log in with the new password newpassword123")
	public void i_should_be_able_to_log_in_with_the_new_password_newpassword123() {
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.findElement(By.name("username")).clear();
	    driver.findElement(By.name("username")).sendKeys("j2ee");
	    
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys("newPassword123");
	    driver.findElement(By.name("signon")).click();
	}




	@When("I attempt to change my password by entering two mismatching values")
	public void i_attempted_non_matching_values() {
		driver.findElement(By.linkText("My Account")).click();
		
		driver.findElement(By.name("password")).sendKeys("newpassword123");
        driver.findElement(By.name("repeatedPassword")).sendKeys("newpassword");
        driver.findElement(By.name("editAccount")).click();
	}
	
	@Then("I should see an error message Passwords do not match")
	public void i_should_see_an_error_passwords_dont_match() {
		WebElement error = driver.findElement(By.className("messages"));
		assertTrue(error.isDisplayed());
	}
	
	@Given("I am logged in to JPetStore with username j2ee and password j2ee")
	public void i_am_logged_in_with_account() {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("j2ee");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("j2ee");
		
		driver.findElement(By.name("signon")).click();
	}
	
	@Given("I am logged in to JPetStore with username j2ee and password newpassword123")
	public void i_am_logged_in_with_new_password() {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("j2ee");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("newpassword123");
		
		driver.findElement(By.name("signon")).click();
	}
	
	/**
	 * This one will fail on purpose as there is no confirmation message displayed. 
	 */
	@Given("I should be redirected back to home page")
	public void i_should_see_a_confirmation_message() {
		assertTrue(driver.getCurrentUrl().contains("35.208.95.33:8080"));

	}
	
	@When("I change my password to p")
	public void i_changed_password_to_p() {
    	driver.findElement(By.linkText("My Account")).click();
		
		driver.findElement(By.name("password")).sendKeys("p");
        driver.findElement(By.name("repeatedPassword")).sendKeys("p");
        driver.findElement(By.name("editAccount")).click();
	}
	
	@Then("I should see an error message Password must be at least eight characters long")
	public void see_error_message_characters_long() {
		WebElement error = driver.findElement(By.className("messages"));
		
		if(error == null) {
			fail();
		}
		assertTrue(error.isDisplayed());
	}
    
	@Given("I am logged in to JPetStore with username quality1 and password")
	public void logged_in_with_quality(){
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("quality1");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("12345678");
		
		driver.findElement(By.name("signon")).click();
	}
	
	@Given("I am logged in to JPetStore with username quality2 and password")
	public void logged_in_with_quality2() {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("quality2");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("12345678");
		
		driver.findElement(By.name("signon")).click();
	}
	
	@AfterAll
    public static void tearDown() {
		
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/jpetstore/actions/Account.action;?signonForm=");
        
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("j2ee");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("newpassword123");
		
		driver.findElement(By.name("signon")).click();
		
		driver.findElement(By.linkText("My Account")).click();
		
		driver.findElement(By.name("password")).sendKeys("j2ee");
        driver.findElement(By.name("repeatedPassword")).sendKeys("j2ee");
        driver.findElement(By.name("editAccount")).click();
    }
	
	@Then("I should see an error message Invalid username or password. Sign in failed.")
	public void see_an_error_message_invalid_user() {
		
		assertTrue(driver.getCurrentUrl().contains("http://35.208.95.33:8080/jpetstore/actions/Account.action"));
	}
	
	@Given("I am logged in to JPetStore with an account")
	public void logged_in() {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("quality1");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("12345678");
		
		driver.findElement(By.name("signon")).click();
	}
	
	@When("I enter an empty username and password ")
	public void enter_username_password() {
		driver.findElement(By.name("username")).sendKeys(" ");

		driver.findElement(By.name("password")).sendKeys(" ");
		
		driver.findElement(By.name("signon")).click();
	}
	
	@When("I enter username J2EE and password J2EE")
	public void enter_wrong_username() {
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("username")).sendKeys("J2EE");

		// Clear the password field before entering the password.
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys("J2EE");
		
		driver.findElement(By.name("signon")).click();
	}
	@Then("I should see an error message Invalid username or password")
	public void error_message_password_invalid() {
		WebElement error = driver.findElement(By.className("messages"));
		assertTrue(error.isDisplayed());
	}
	
	@Given("I am on the {string} page")
    public void i_am_on_the_first_page(String page) {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");        
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/jpetstore/actions/Account.action?newAccountForm=");
    }    

    @When("I enter mismatching password:")
    public void i_enter_the_following_user_details() {
    driver.findElement(By.name("username")).sendKeys("sss");
        driver.findElement(By.name("password")).sendKeys("Pass1234");
        driver.findElement(By.name("repeatedPassword")).sendKeys("Pass12344");
        
    }

    @And("I click the {string} button")
    public void i_click_the_button(String button) {
        driver.findElement(By.name("newAccount")).click();
    }

    @Then("I should receive an error message")
    public void i_should_receive_an_error_message_dup() {
    WebElement error = driver.findElement(By.className("messages"));
        assertTrue(error.isDisplayed());
    }



    @When("I click the {string} button without entering any details")
    public void i_click_the_button_without_entering_any_details(String buttonName) {
        driver.findElement(By.name("newAccount")).click();
    }

    @Then("I should get an error message")
    public void error_message_then() {
    try {
    	WebElement error = driver.findElement(By.className("messages"));
        assertTrue(error.isDisplayed());

    }catch(Exception e) {
    	fail();

    }
   }
    

    @When("I click the {string} button without entering required details")
public void i_click_the_button_without_entering_some_details(String buttonName) {
      driver.findElement(By.name("username")).sendKeys("j2ee");
        driver.findElement(By.name("password")).sendKeys("j2ee");
        driver.findElement(By.name("repeatedPassword")).sendKeys("j2ee");
    
    driver.findElement(By.name("newAccount")).click();
}

@Then("I should get a error message")
public void should_got_a_error_message() {
try {
WebElement error = driver.findElement(By.className("messages"));
        assertTrue(error.isDisplayed());

}catch(Exception e) {
fail();

}
}

@Given("an existing user ID {string}")
public void an_existing_user_id(String userID) {
  
  ;
}

@When("I enter all user details:")
public void i_enter_all_user_details() {
    driver.findElement(By.name("username")).sendKeys("j2ee"); 
    driver.findElement(By.name("account.email")).sendKeys("utsavg03@gmail.com");
    driver.findElement(By.name("password")).sendKeys("Pass1234");
    driver.findElement(By.name("repeatedPassword")).sendKeys("Pass1234");
    driver.findElement(By.name("account.firstName")).sendKeys("John");
    driver.findElement(By.name("account.lastName")).sendKeys("Doe");
    driver.findElement(By.name("account.phone")).sendKeys("1234567890");
    driver.findElement(By.name("account.address1")).sendKeys("123 Main St");
    driver.findElement(By.name("account.city")).sendKeys("Calgary");
    driver.findElement(By.name("account.state")).sendKeys("AB");
    driver.findElement(By.name("account.zip")).sendKeys("12345");
    driver.findElement(By.name("account.country")).sendKeys("Canada");
}

@When ("I click the _button_")
public void i_click_the_buttons_() {
driver.findElement(By.name("newAccount")).click();
}

@Then("I should receive an error _message_")
public void i_should_receive_an_error_messages_() {
try {
WebElement error = driver.findElement(By.className("messages"));
        assertTrue(error.isDisplayed());

}catch(Exception e) {
fail();

}
}






@Given("an existing email {string}")
public void an_existing_email(String userID) {
  
  ;
}

@When("I enter all user _details_:")
public void i_enter_all_user_details_() {
    driver.findElement(By.name("username")).sendKeys("j2ee"); 
    driver.findElement(By.name("account.email")).sendKeys("user@test.com");
    driver.findElement(By.name("password")).sendKeys("Pass1234");
    driver.findElement(By.name("repeatedPassword")).sendKeys("Pass1234");
    driver.findElement(By.name("account.firstName")).sendKeys("John");
    driver.findElement(By.name("account.lastName")).sendKeys("Doe");
    driver.findElement(By.name("account.phone")).sendKeys("1234567890");
    driver.findElement(By.name("account.address1")).sendKeys("123 Main St");
    driver.findElement(By.name("account.city")).sendKeys("Calgary");
    driver.findElement(By.name("account.state")).sendKeys("AB");
    driver.findElement(By.name("account.zip")).sendKeys("12345");
    driver.findElement(By.name("account.country")).sendKeys("Canada");
}

@When ("I click the button")
public void i_click_the_button_() {
driver.findElement(By.name("newAccount")).click();
}

//@Then("I should receive an error message")
//public void i_should_receive_an_error_message_() {
//try {
//WebElement error = driver.findElement(By.className("messages"));
//        assertTrue(error.isDisplayed());
//
//}catch(Exception e) {
//fail();
//
//}
//}


	
}


  
	/*
	 * @When("I enter invalid user details with weak password") public void
	 * registration_with_invalid_password_complexity() {
	 * driver.get("http://example.com/signup");
	 * 
	 * // When: User enters the following details with a weak password
	 * driver.findElement(By.name("username")).sendKeys("user123");
	 * driver.findElement(By.name("email")).sendKeys("user@test.com");
	 * driver.findElement(By.name("password")).sendKeys("pass"); // Too short
	 * driver.findElement(By.name("confirmPassword")).sendKeys("pass");
	 * driver.findElement(By.name("firstName")).sendKeys("John");
	 * driver.findElement(By.name("lastName")).sendKeys("Doe");
	 * driver.findElement(By.name("phone")).sendKeys("1234567890");
	 * driver.findElement(By.name("address1")).sendKeys("123 Main St");
	 * driver.findElement(By.name("city")).sendKeys("Calgary");
	 * driver.findElement(By.name("state")).sendKeys("AB");
	 * driver.findElement(By.name("zip")).sendKeys("12345");
	 * driver.findElement(By.name("country")).sendKeys("Canada");
	 * driver.findElement(By.name("submit")).click();
	 * 
	 * @Then: System should show an error message WebElement errorMessage =
	 * driver.findElement(By.cssSelector("#Content .messages li")); asse
	 *rtTrue(errorMessage.isDisplayed()); // Verify that the error message is displayed
    }
*/
    	

