import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * As a craigslist user
 * I would like to be able to log in
 * So that I can access my postings
 */

public class CraigslistLoginTest {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://pittsburgh.craigslist.org/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  // Given a correct email
  // And a correct password
  // When I try to log in
  // Then I should be logged in
  
  @Test
  public void testCorrectLogin() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
    driver.findElement(By.id("inputEmailHandle")).clear();
    driver.findElement(By.id("inputEmailHandle")).sendKeys("cs1632test@gmail.com");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("stupidpassword123");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
	String URL = driver.getCurrentUrl();
	Assert.assertEquals(URL, "https://accounts.craigslist.org/login/home" );
  }
  
  // Given an empty email
  // And an empty password
  // When I try to log in
  // Then the page should not change and wait for an email and password
  
  @Test
  public void testEmptyFields() throws Exception {
    driver.get(baseUrl + "/");
    String prevURL = driver.getCurrentUrl();
    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    Assert.assertEquals("http://pittsburgh.craigslist.org//", prevURL);
  }


  // Given a incorrect email
  // And a correct password
  // When I try to log in
  // Then an indication should show I have entered an incorrect password or email
  
  @Test
  public void testIncorrectEmail() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
    driver.findElement(By.id("inputEmailHandle")).clear();
    driver.findElement(By.id("inputEmailHandle")).sendKeys("1234");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("stupidpassword123");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    assertTrue(isElementPresent(By.cssSelector("p.error")));
  }
  
  // Given a correct email
  // And a incorrect password
  // When I try to log in
  // Then an indication should show I have entered an incorrect password or email
  
  @Test
  public void testIncorrectPassword() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.xpath("(//a[contains(text(),'my account')])[2]")).click();
    driver.findElement(By.id("inputEmailHandle")).clear();
    driver.findElement(By.id("inputEmailHandle")).sendKeys("cs1632test@gmail.com");
    driver.findElement(By.id("inputPassword")).clear();
    driver.findElement(By.id("inputPassword")).sendKeys("1234");
    driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
    assertTrue(isElementPresent(By.cssSelector("p.error")));
  }
  
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}