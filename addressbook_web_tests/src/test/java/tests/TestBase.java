package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;

public class TestBase {
    protected static ApplicationManager app;
    public WebDriver driver;

    @BeforeEach
    public void setUp() {
        if(app == null) {
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser","firefox"));
    }

    public void createContact() {
      driver.findElement(By.linkText("add new")).click();
      driver.findElement(By.name("firstname")).click();
      driver.findElement(By.name("firstname")).sendKeys("test1");
      driver.findElement(By.name("middlename")).click();
      driver.findElement(By.name("middlename")).sendKeys("test1");
      driver.findElement(By.name("lastname")).click();
      driver.findElement(By.name("lastname")).sendKeys("test1");
      driver.findElement(By.name("nickname")).click();
      driver.findElement(By.name("nickname")).sendKeys("test1");
      driver.findElement(By.name("title")).click();
      driver.findElement(By.name("title")).sendKeys("test1");
      driver.findElement(By.name("company")).click();
      driver.findElement(By.name("company")).sendKeys("test1");
      driver.findElement(By.name("address")).click();
      driver.findElement(By.name("address")).sendKeys("test1");
      driver.findElement(By.name("home")).click();
      driver.findElement(By.name("home")).sendKeys("951");
      driver.findElement(By.name("mobile")).click();
      driver.findElement(By.name("mobile")).sendKeys("357");
      driver.findElement(By.name("work")).click();
      driver.findElement(By.name("work")).sendKeys("654");
      driver.findElement(By.name("fax")).click();
      driver.findElement(By.name("fax")).click();
      driver.findElement(By.name("email")).click();
      driver.findElement(By.name("email")).sendKeys("test@test.ru");
      driver.findElement(By.name("email2")).click();
      driver.findElement(By.name("email2")).sendKeys("test@test.ru");
      driver.findElement(By.name("email3")).click();
      driver.findElement(By.name("email3")).sendKeys("test@test.ru");
      driver.findElement(By.name("homepage")).click();
      driver.findElement(By.name("bday")).click();
      {
        WebElement dropdown = driver.findElement(By.name("bday"));
        dropdown.findElement(By.xpath("//option[. = '17']")).click();
      }
      driver.findElement(By.name("bmonth")).click();
      {
        WebElement dropdown = driver.findElement(By.name("bmonth"));
        dropdown.findElement(By.xpath("//option[. = 'August']")).click();
      }
      driver.findElement(By.name("byear")).click();
      driver.findElement(By.name("byear")).sendKeys("1950");
      driver.findElement(By.name("aday")).click();
      {
        WebElement dropdown = driver.findElement(By.name("aday"));
        dropdown.findElement(By.xpath("//option[. = '15']")).click();
      }
      driver.findElement(By.name("amonth")).click();
      {
        WebElement dropdown = driver.findElement(By.name("amonth"));
        dropdown.findElement(By.xpath("//option[. = 'October']")).click();
      }
      driver.findElement(By.name("ayear")).click();
      driver.findElement(By.name("ayear")).sendKeys("1975");
      driver.findElement(By.name("new_group")).click();
      {
        WebElement dropdown = driver.findElement(By.name("new_group"));
        dropdown.findElement(By.xpath("//option[. = 'test_1']")).click();
      }
      driver.findElement(By.cssSelector("input:nth-child(75)")).click();
      driver.findElement(By.linkText("home page")).click();
    }

  public void openHomePage() {
    driver.findElement(By.linkText("home")).click();
  }

  public void deleteContact() {
    driver.findElement(By.name("selected[]")).click();
    driver.findElement(By.xpath("//input[@value=\'Delete\']")).click();
  }

  public boolean isContactPresent() {
    return isElementPresent(By.name("selected[]"));
  }

  private boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException exception) {
      return false;
    }
  }
}
