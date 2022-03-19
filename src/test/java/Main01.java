import net.jodah.failsafe.internal.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main01 {

    private static WebDriver driver;

    //ljvfwmvimbbjdpikhc@nvhrw.com
    //lorem.ipsum@mail.com
    //lorem123

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl");
    }

    @Test
    public void testLogIn(){
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSignIn();

        LogInPage logInPage = new LogInPage(driver);
        logInPage.SignIn("lorem.ipsum@mail.com", "lorem123");

        YourAccount yourAccount = new YourAccount(driver);
        yourAccount.clickOnAddresses();

        YourAddresses yourAddresses = new YourAddresses(driver);
        yourAddresses.addNewAddress("NextAddress","Ox Avvenue","London", "12345", "United Kingdom", "111222333");

        yourAddresses.checkSecondAddressDetails("NextAddress","Ox Avvenue","London", "12345", "United Kingdom", "111222333");
        yourAddresses.deleteSecondAddress();

        Boolean addressRemoval = yourAddresses.checkingForSecondAddressRemoval();

        Assert.isTrue(addressRemoval, "Address deletion failed. The second address is still visible.");

    }

}
