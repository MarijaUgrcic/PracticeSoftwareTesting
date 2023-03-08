import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Utils;

import java.time.Duration;

public class RegistrationTest {
    WebDriver driver;
    WebDriverWait wait;
    By signInButton = By.cssSelector("a[data-test='nav-sign-in']");
    By registerYourAccountLink = By.cssSelector("a[data-test='register-link']");
    By firstName = By.id("first_name");
    By lastName = By.id("last_name");
    By dateOFBirth = By.id("dob");
    By address = By.id("address");
    By postcode = By.id("postcode");
    By city = By.id("city");
    By state = By.id("state");
    By country = By.id("country");
    By phone = By.id("phone");
    By email = By.id("email");
    By password = By.id("password");
    By registerButton = By.cssSelector("button[data-test='register-submit']");
    By loginButton = By.cssSelector("input[value='Login']");
    By myAccountText = By.xpath("//h1[text()='My account']");
    By welcomeText = By.xpath("//p[text()='Here you can manage your profile, favorites and orders.']");
    Keys tab = Keys.TAB;
    String randomNewMail;

    public WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickOnElement(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void typeIn(By locator, String text) {
        getElement(locator).sendKeys(text);
    }

    public String getTextFromElement(By locator) {
        return getElement(locator).getText();
    }

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        Utils.waitForMillis(1000);
        driver.manage().window().maximize();
        driver.get("https://practicesoftwaretesting.com/#/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void registration() {
        clickOnElement(signInButton);
        clickOnElement(registerYourAccountLink);
        typeIn(firstName, "Jana");
        typeIn(lastName, "Janic");
        typeIn(dateOFBirth, "01Jan" + tab + "1990");
        typeIn(address, "Beogradska 1");
        typeIn(postcode, "11000");
        typeIn(city, "Belgrade");
        typeIn(state, "Serbia");
        typeIn(country, "Serbia");
        typeIn(phone, "011123456");
        randomNewMail = Utils.randomMail();
        typeIn(email, randomNewMail);
        typeIn(password, "password1");
        clickOnElement(registerButton);
        Utils.waitForMillis(1000);
        typeIn(email, randomNewMail);
        typeIn(password, "password1");
        clickOnElement(loginButton);

        String actualAccountText = getTextFromElement(myAccountText);
        System.out.println(actualAccountText);
        String expectedText = "My account";
        Assert.assertEquals(actualAccountText, expectedText);

        String actualWelcomeText = getTextFromElement(welcomeText);
        System.out.println(actualWelcomeText);
        String expectedWelcomeText = "Here you can manage your profile, favorites and orders.";
        Assert.assertEquals(actualWelcomeText, expectedWelcomeText);

    }

}
