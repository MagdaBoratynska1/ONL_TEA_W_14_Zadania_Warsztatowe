import net.jodah.failsafe.internal.util.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class OrderPage {

    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmAddress() {
        driver.findElement(By.name("confirm-addresses")).click();
    }

    public void confirmDeliveryOption() {
        driver.findElement(By.xpath("//span[text()='Pick up in-store']")).click();
        driver.findElement(By.name("confirmDeliveryOption")).click();
    }
//Pay by Check

    public void confirmPaymentMethod() {
        driver.findElement(By.xpath("//span[text()='Pay by Check']")).click();
        driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();
        //Order with an obligation to pay
        driver.findElement(By.xpath("//button[@class='btn btn-primary center-block']")).click();

    }

    public String saveTotalPrice(){
        String price = driver.findElement(By.xpath("//tr[@class='font-weight-bold']/td[2]")).getText();

        return price;
    }

    public String saveOrderID(){
        String orderReference = driver.findElement(By.xpath("//div[@id ='order-details']/ul[1]/li[1]")).getText();
        String orderID = orderReference.substring(17);

        return orderID;
    }

    public void takeScreenShot(){
        //Take the screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File(".\\test-output\\orderConfirmation.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
