import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOnSignIn(){
        WebElement signInLink = driver.findElement(By.xpath("//*[@id='_desktop_user_info']/div/a"));
        signInLink.click();
    }
}
