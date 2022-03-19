import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class YourAddresses {

    private WebDriver driver;

    public YourAddresses(WebDriver driver){
        this.driver = driver;
    }

    public void addNewAddress(String alias, String address, String city, String postCode, String country, String phone){
        WebElement addressesButton = driver.findElement(By.xpath("//*[@class='addresses-footer']/a"));
        addressesButton.click();

        // Completing the form
        driver.findElement(By.name("alias")).sendKeys(alias);
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("postcode")).sendKeys(postCode);
        driver.findElement(By.name("id_country")).click();
        String countryXPATH = "//option[text()='" + country + "']";
        driver.findElement(By.xpath(countryXPATH)).click();
        driver.findElement(By.name("phone")).sendKeys(phone);

        WebElement saveButton = driver.findElement(By.xpath("//*[@id='content']/div/div/form/footer/button"));
        saveButton.click();
    }


    public void checkSecondAddressDetails(String alias, String address, String city, String postCode, String country, String phone){
        WebElement addressDetailsButton = driver.findElement(By.xpath("//*[@id ='content']/div[2]/article/div[2]/a[1]/span"));
        addressDetailsButton.click();

        String addressAlias = driver.findElement(By.name("alias")).getAttribute("value");
        String addressAddress = driver.findElement(By.name("address1")).getAttribute("value");
        String addressCity = driver.findElement(By.name("city")).getAttribute("value");
        String addressPostCode = driver.findElement(By.name("postcode")).getAttribute("value");
        String addressPhone = driver.findElement(By.name("phone")).getAttribute("value");

        //Reading selected value from combobox "Country"
        Select comboBox = new Select(driver.findElement(By.name("id_country")));
        String selectedCountryValue = comboBox.getFirstSelectedOption().getText();

        Assert.isTrue(addressAlias.equals(alias),"Test failed. Alis mismatch");
        Assert.isTrue(addressAddress.equals(address),"Test failed. Address mismatch");
        Assert.isTrue(addressCity.equals(city),"Test failed. City mismatch");
        Assert.isTrue(addressPostCode.equals(postCode),"Test failed. Post Code mismatch");
        Assert.isTrue(selectedCountryValue.equals(country),"Test failed. Country mismatch");
        Assert.isTrue(addressPhone.equals(phone),"Test failed. Phone mismatch");

        driver.navigate().back();
    }

    public void deleteSecondAddress() {
        WebElement addressDeleteButton = driver.findElement(By.xpath("//*[@id ='content']/div[2]/article/div[2]/a[2]/span"));
        addressDeleteButton.click();

    }

    public boolean checkingForSecondAddressRemoval(){
        //If the removal of the address was successful, return true
        try{
            driver.findElement(By.xpath("//*[@id ='content']/div[2]/article/div[2]/a[2]/span"));
            return false;
        }
        catch(NoSuchElementException e){
            return true;
        }
    }

}
