//package my.domain.name.FBLogin1.steps;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//import static org.junit.Assert.*;
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.When;
//import io.cucumber.java.en.Then;
//public class ChangePasswordSteps {
//	
//	WebDriver driver;
//	
//	
//
//	@Given("I am logged in to JPetStore with username 'j2ee' and password 'j2ee'")
//	public void i_am_logged_in_with_account() {
//        driver.findElement(By.name("username")).sendKeys("j2ee");
//        driver.findElement(By.name("password")).sendKeys("j2ee");
//        driver.findElement(By.name("signon")).click();
//	}
//	
//	@When("When I change my password to \"newpassword123\"")
//	public void successful_password_change() {
//		driver.findElement(By.linkText("My Account")).click();
//		
//		driver.findElement(By.name("password")).sendKeys("newpassword123");
//        driver.findElement(By.name("repeatedPassword")).sendKeys("newpassword123");
//        driver.findElement(By.name("editAccount")).click();
//	}
//	
//	@Then("Then I should see a confirmation message \"Your password has been updated.")
//	public void i_should_see_a_confirmation_message() {
//		driver.get("http://35.208.95.33:8080/jpetstore/actions/Catalog.action");
//	}
//	
//	@Then("I should be able to log in with the new password \"newpassword123\"")
//	void i_should_be_able_to_login() {
//		driver.findElement(By.linkText("Sign Out")).click();
//		
//	    driver.findElement(By.name("username")).sendKeys("j2ee");
//	    driver.findElement(By.name("password")).sendKeys("newPassword123");
//	    driver.findElement(By.name("signon")).click();
//	}
//	
//	@When("I attempt to change my password by entering two mismatching values")
//	public void i_attempted_non_matching_values() {
//		driver.findElement(By.linkText("My Account")).click();
//		
//		driver.findElement(By.name("password")).sendKeys("newpassword123");
//        driver.findElement(By.name("repeatedPassword")).sendKeys("newpassword");
//        driver.findElement(By.name("editAccount")).click();
//	}
//	
//
//}
