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

    public void clickOnLogo(){
        WebElement addressesButton = driver.findElement(By.xpath("//*[@id='_desktop_logo']/a"));
        addressesButton.click();
    }

    public void clickOnOrderHistory(){
        WebElement historyButton = driver.findElement(By.xpath("//*[@id='history-link']/span"));
        historyButton.click();
    }
}
