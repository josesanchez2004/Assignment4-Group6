package my.domain.name.FBLogin1.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class LoginSteps {
    WebDriver driver;

    @Given("I am on the JPetStore login page")
    public void i_am_on_the_j_pet_store_login_page() {
        System.setProperty("webdriver.chrome.driver", "msedgedriver.exe");
        driver = new EdgeDriver();
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
}
