import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class YourAccount {
    private WebDriver driver;

    public YourAccount(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnAddresses(){
        WebElement addressesButton = driver.findElement(By.xpath("//*[@id='addresses-link']/span"));
        addressesButton.click();

    }
}
