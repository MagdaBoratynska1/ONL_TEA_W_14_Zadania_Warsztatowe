import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class MyStoreSteps {

    WebDriver driver;

    @Given("The user is on {string} website")
    public void theUserIsOnWebsite(String url) {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @When("The user clicks on SignIn button")
    public void theUserClicksOnSignInButton() {
        HomePage homePage = new HomePage(driver);
        homePage.clickOnSignIn();
    }

    @And("The user inputs an email address {string} and a password {string} and clicks the SignIn button")
    public void theUserInputsAnEmailAddressAndAPasswordAndClicksTheSignInButton(String email, String password) {
        LogInPage logInPage = new LogInPage(driver);
        logInPage.SignIn(email, password);
    }

    @And("The user clicks the Addresses section")
    public void theUserClicksTheAddressesSectionAndClicksCreateNewAddressButton() {
        YourAccount yourAccount = new YourAccount(driver);
        yourAccount.clickOnAddresses();

    }

    @And("The user clicks Create new address button and completes the form {string}, {string}, {string} , {string}, {string} , {string} and clicks Save button")
    public void theUserCompletesTheFormAndClicksSaveButton(String alias, String address, String city, String postCode, String country, String phone) {
        YourAddresses yourAddresses = new YourAddresses(driver);
        yourAddresses.addNewAddress(alias,address,city, postCode, country, phone);

    }

    @And("Checking the correctness of the new address data {string}, {string}, {string} , {string}, {string} , {string}")
    public void checkingTheCorrectnessOfTheNewAddressData(String alias, String address, String city, String postCode, String country, String phone) {
        YourAddresses yourAddresses = new YourAddresses(driver);
        yourAddresses.checkSecondAddressDetails(alias,address,city, postCode, country, phone);

    }

    @And("The user deletes the address")
    public void theUserDeletesTheAddress() {
        YourAddresses yourAddresses = new YourAddresses(driver);
        yourAddresses.deleteSecondAddress();
    }

    @Then("The newly added user address has been successfully deleted")
    public void theNewlyAddedUserAddressHasBeenSuccessfullyDeleted() {
        YourAddresses yourAddresses = new YourAddresses(driver);
        Boolean addressRemoval = yourAddresses.checkingForSecondAddressRemoval();
        Assert.isTrue(addressRemoval, "Address deletion failed. The second address is still visible.");

        driver.quit();
    }

    @And("The user goes to the home page and selects a Hummingbird Printed Sweater")
    public void theUserGoesToTheHomePageAndSelectsAHummingbirdPrintedSweater() {
        YourAccount yourAccount = new YourAccount(driver);
        HomePage homePage = new HomePage(driver);
        yourAccount.clickOnLogo();

        homePage.clickOnSweater();
    }

    @And("The user checks the discount, enters the size {string} and quantity {string}")
    public void theUserChecksTheDiscountEntersTheSizeAndQuantity(String size, String quantity) throws InterruptedException{
        ProductPage productPage = new ProductPage(driver);
        productPage.checkDiscount();
        productPage.sizeSelection(size);
        productPage.quantityChoice(quantity);
    }

    @And("The user adds products to the cart and proceeds to checkout")
    public void theUserAddsProductsToTheCartAndProceedsToCheckout() throws InterruptedException{
        ProductPage productPage = new ProductPage(driver);
        Thread.sleep(1000);
        productPage.addToCart();

        Thread.sleep(1000);
        productPage.proceedToCheckout();
        productPage.proceedToCheckout();
    }

    @And("The user fills in the order data and confirms the order \\(screenshot)")
    public void theUserFillsInTheOrderDataAndConfirmsTheOrderScreenshot() {
        OrderPage orderPage = new OrderPage(driver);
        orderPage.confirmAddress();
        orderPage.confirmDeliveryOption();
        orderPage.confirmPaymentMethod();
        orderPage.takeScreenShot();
    }

    @Then("The data in the Order History is consistent with the created order")
    public void theDataInTheOrderHistoryIsConsistentWithTheCreatedOrder() {
        OrderPage orderPage = new OrderPage(driver);
        HomePage homePage = new HomePage(driver);
        YourAccount yourAccount = new YourAccount(driver);

        String orderID = orderPage.saveOrderID();
        String orderPrice = orderPage.saveTotalPrice();
        homePage.clickOnName();
        yourAccount.clickOnOrderHistory();

        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        orderHistoryPage.checkOrderReference(orderID);
        orderHistoryPage.checkOrderPrice(orderPrice);
        orderHistoryPage.checkOrderStatus();

        driver.quit();
    }
}
