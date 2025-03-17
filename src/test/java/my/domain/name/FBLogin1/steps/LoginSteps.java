package my.domain.name.FBLogin1.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {
    WebDriver driver;

    @Given("I am on the JPetStore login page")
    public void i_am_on_the_j_pet_store_login_page() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/jpetstore/actions/Account.action;jsessionid=E52337A8CB1611E88D1085A7C25CDCCE?signonForm=");
    }

    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
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
        assertTrue(driver.getCurrentUrl().contains("main"));
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
    
    @Given("I am logged in to JPetStore with an account")
    public void i_should_be_logged_in() {
        driver.findElement(By.name("username")).sendKeys("j2ee");
        driver.findElement(By.name("password")).sendKeys("j2ee");
        driver.findElement(By.name("signon")).click();
    }
    
    @When("I sign out")
    public void i_should_sign_out() {
    	driver.findElement(By.linkText("Sign Out")).click();
    }
    
    @When("I should not have access to account services")
    public void i_should_not_have_access() {
    	driver.get("http://35.208.95.33:8080/jpetstore");
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
    	
}
