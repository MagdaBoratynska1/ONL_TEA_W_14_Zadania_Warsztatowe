import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.swing.*;

public class OrderHistoryPage {
    private WebDriver driver;

    public OrderHistoryPage(WebDriver driver) {
        this.driver = driver;
    }


    public void checkOrderReference(String orderID){
        String orderReference = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/th")).getText();
        System.out.println("Order ID on the Order Confirmation: " + orderID);
        System.out.println("Order ID on the Order History: " + orderReference);
        Assert.isTrue(orderReference.equals(orderID), "Invalid order number");
    }

    public void checkOrderPrice(String price){
        String totalPrice = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[2]")).getText();
        System.out.println("Price on the Order Confirmation: " + price);
        System.out.println("Price on the Order History: " + totalPrice);
        Assert.isTrue(totalPrice.equals(price), "Invalid order price");
    }

    public void checkOrderStatus(){
        String orderStatus = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[4]/span")).getText();
        String expectedStatus = "Awaiting check payment";
        System.out.println("Expected Status: " + orderStatus);
        System.out.println("Status on the Order History: " + orderStatus);
        Assert.isTrue(orderStatus.contains(expectedStatus), "Invalid order price");
    }
}
