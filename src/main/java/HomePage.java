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

    //[@id='content']/section/div/article[2]/div/div[1]/h3/a
    public void clickOnSweater(){
        WebElement hummingbirdSweater = driver.findElement(By.xpath("//*[@class='products']/article/div/div[1]/h3/a[text()='Hummingbird printed sweater']"));
        hummingbirdSweater.click();
    }

    public void clickOnName(){
        driver.findElement(By.xpath("//span[@class='hidden-sm-down']")).click();
    }

}
