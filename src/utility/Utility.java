package utility;

import browsertesting.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    /**
     * Click on element using 'By'
     */
    public void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    /**
     * Click on element using 'WebElement'
     */
    public void clickOnElement(WebElement element) {
        element.click();
    }

    /**
     * select value from dropdown by 'Value'
     */
    public void selectElementFromDropDownByValue(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByValue(text);
    }

    /**
     * select value from dropdown by 'Index'
     */
    public void selectElementFromDropDownByIndex(By by, int index) {
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    /**
     * select value from dropdown by 'Visible Text'
     */
    public void selectElementFromDropDownByVisibleText(By by, String text) {
        WebElement element = driver.findElement(by);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }


    public void assertToVerify(By by, String expected) {
        String actual = getTextFromElement(by);
        Assert.assertEquals("Error= Assertion Failed", expected, actual);

    }

    public void assertToVerifyByAttribute(By by, String attribute, String expected){
        String actual = driver.findElement(by).getAttribute(attribute);
        Assert.assertEquals("Error= Assertion Failed", expected, actual);


    }

    /**
     * returns text from element
     */
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    /**
     * send text from element
     */
    public void sendTextFromElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }


    /**
     * This method will hover the mouse over a particular element and click it
     *
     * @param by
     */
    public void hoverMouseToElementAndClick(By by) {
        Actions hover = new Actions(driver);
        WebElement a = driver.findElement(by);
        hover.moveToElement(a).click().build().perform();
    }

    public void hoverMouseToElementAndClick(WebElement element) {
        Actions hover = new Actions(driver);
        hover.moveToElement(element).click().build().perform();
    }

    /**
     * This method will use to hover mouse on element
     */
    public void hoverMouseToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    /**
     * find the element and clear all the data from it
     *
     * @param by
     */
    public void clearValueFromElement(By by) {
        WebElement element = driver.findElement(by);
        element.clear();
    }

    /**
     * send text to element
     */
    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    public String doGetRandomString(int length) {
        String randString;
        RandomString rnd = new RandomString(length);
        return randString = rnd.nextString();
    }



}

