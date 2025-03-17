import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegistrationTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://35.208.95.33:8080/actions/Account.action?signonForm=");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testMismatchedPasswords() {
        fillForm("12345678", "user@test.com", "Pass1234", "Pass5678", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testEmptyFields() {
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testMissingFirstName() {
        fillForm("12345678", "user@test.com", "SecurePass123", "SecurePass123", "", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testMissingRequiredFields() {
        String[][] fields = {
            {"Last Name", "Last name is mandatory"},
            {"Email", "Email is mandatory"},
            {"Phone", "Phone number is mandatory"},
            {"Address 1", "Address 1 is mandatory"},
            {"City", "City is mandatory"},
            {"State", "State is mandatory"},
            {"Zip", "Zip code is mandatory"},
            {"Country", "Country is mandatory"}
        };

        for (String[] field : fields) {
            fillForm("12345678", "user@test.com", "SecurePass123", "SecurePass123", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
            clearField(field[0]); 
            driver.findElement(By.name("newAccount")).click();

            assertTrue(isErrorMessageDisplayed());
        }
    }

    @Test
    public void testValidRegistrationWithLastNameAsUsername() {
        fillForm("Doe", "user@test.com", "SecurePass123", "SecurePass123", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(driver.getCurrentUrl().contains("main"));
    }

    @Test
    public void testValidRegistrationWithEmptyAddress2() {
        fillForm("12345678", "user@test.com", "SecurePass123", "SecurePass123", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("account.address2")).clear(); 
        driver.findElement(By.name("newAccount")).click();

        assertTrue(driver.getCurrentUrl().contains("main"));
    }

    @Test
    public void testDuplicateUserId() {
        fillForm("existinguser", "user@test.com", "SecurePass123", "SecurePass123", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testInvalidPasswordComplexity() {
        fillForm("user123", "user@test.com", "pass", "pass", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    @Test
    public void testDuplicateEmail() {
        fillForm("user123", "existing@test.com", "SecurePass123", "SecurePass123", "John", "Doe", "1234567890", "123 Main St", "Calgary", "AB", "12345", "Canada");
        driver.findElement(By.name("newAccount")).click();

        assertTrue(isErrorMessageDisplayed());
    }

    // Helper method to fill the registration form
    private void fillForm(String username, String email, String password, String confirmPassword, String firstName, String lastName, String phone, String address1, String city, String state, String zip, String country) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("account.email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("repeatedPassword")).sendKeys(confirmPassword);
        driver.findElement(By.name("account.firstName")).sendKeys(firstName);
        driver.findElement(By.name("account.lastName")).sendKeys(lastName);
        driver.findElement(By.name("account.phone")).sendKeys(phone);
        driver.findElement(By.name("account.address1")).sendKeys(address1);
        driver.findElement(By.name("account.city")).sendKeys(city);
        driver.findElement(By.name("account.state")).sendKeys(state);
        driver.findElement(By.name("account.zip")).sendKeys(zip);
        driver.findElement(By.name("account.country")).sendKeys(country);
    }

    private void clearField(String fieldName) {
        driver.findElement(By.name("account." + fieldName.replace(" ", "").toLowerCase())).clear();
    }

    private boolean isErrorMessageDisplayed() {
        try {
            WebElement errorMessage = driver.findElement(By.cssSelector("ul.messages li"));
            return !errorMessage.getText().isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
