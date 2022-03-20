import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkDiscount() {
        WebElement discount = driver.findElement(By.cssSelector("span[class = 'discount discount-percentage']"));
        String disc = discount.getText();
        Assert.isTrue(disc.contains("20%"), "The discount on this product is not 20%");
    }

    public void sizeSelection(String size) {
        driver.findElement(By.id("group_1")).click();
        String xpathSize = "//*[@id='group_1']/option[@title = '" + size + "']";
        WebElement sizeSelect = driver.findElement(By.xpath(xpathSize));
        sizeSelect.click();
    }

    public void quantityChoice(String quantity)throws InterruptedException {
        WebElement quantityInput = driver.findElement(By.name("qty"));
        quantityInput.click();
        Thread.sleep(1000);
        quantityInput.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        quantityInput.sendKeys(quantity);
    }

    public void addToCart() {
        driver.findElement(By.xpath("//*[@class='btn btn-primary add-to-cart']")).click();
    }

    public void proceedToCheckout() {
        driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
    }
}
