import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class RegistrationSteps {
    WebDriver driver;

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

    @Then("I should see an error message")
    public void i_should_see_an_error_message() {
        WebElement error = driver.findElement(By.cssSelector("#Content .messages li"));
        assertTrue(error.isDisplayed()); 
    }

    @Then("No user should be created")
    public void no_user_should_be_created() {
        assertTrue(driver.getCurrentUrl().contains("Account.action?newAccountForm"));
    }

    @When("I attempt to register without entering any details")
    public void i_attempt_to_register_without_entering_any_details() {
        driver.findElement(By.name("saveAccountInfo")).click();
    }

    @Then("I should receive an error message")
    public void i_should_receive_an_error_message() {
        WebElement error = driver.findElement(By.cssSelector("#Content .messages li"));
        assertTrue(error.isDisplayed()); 
    }

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

  
    @When("I enter invalid user details with weak password")
    public void registration_with_invalid_password_complexity() {
        driver.get("http://example.com/signup");

        // When: User enters the following details with a weak password
        driver.findElement(By.name("username")).sendKeys("user123");
        driver.findElement(By.name("email")).sendKeys("user@test.com");
        driver.findElement(By.name("password")).sendKeys("pass");  // Too short
        driver.findElement(By.name("confirmPassword")).sendKeys("pass");
        driver.findElement(By.name("firstName")).sendKeys("John");
        driver.findElement(By.name("lastName")).sendKeys("Doe");
        driver.findElement(By.name("phone")).sendKeys("1234567890");
        driver.findElement(By.name("address1")).sendKeys("123 Main St");
        driver.findElement(By.name("city")).sendKeys("Calgary");
        driver.findElement(By.name("state")).sendKeys("AB");
        driver.findElement(By.name("zip")).sendKeys("12345");
        driver.findElement(By.name("country")).sendKeys("Canada");
        driver.findElement(By.name("submit")).click();

        @Then: System should show an error message
        WebElement errorMessage = driver.findElement(By.cssSelector("#Content .messages li"));
        assertTrue(errorMessage.isDisplayed()); // Verify that the error message is displayed
    }
}
