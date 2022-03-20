import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Task2 {

    private static WebDriver driver;

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
    public void testLogIn()throws InterruptedException{
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSignIn();

        LogInPage logInPage = new LogInPage(driver);
        logInPage.SignIn("lorem.ipsum@mail.com", "lorem123");

        YourAccount yourAccount = new YourAccount(driver);
        yourAccount.clickOnLogo();

        homePage.clickOnSweater();

        ProductPage productPage = new ProductPage(driver);
        productPage.checkDiscount();
        productPage.sizeSelection("M");
        productPage.quantityChoice("5");

        Thread.sleep(1000);
        productPage.addToCart();

        Thread.sleep(1000);
        productPage.proceedToCheckout();
        productPage.proceedToCheckout();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.confirmAddress();
        orderPage.confirmDeliveryOption();
        orderPage.confirmPaymentMethod();


        //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File(".\\test-output\\orderConfirmation.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String orderID = orderPage.saveOrderID();
        String orderPrice = orderPage.saveTotalPrice();
        homePage.clickOnName();
        yourAccount.clickOnOrderHistory();



        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.checkOrderReference(orderID);
        orderHistoryPage.checkOrderPrice(orderPrice);
        orderHistoryPage.checkOrderStatus();

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
