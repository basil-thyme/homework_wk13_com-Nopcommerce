package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    @Before
    public void setUp() {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }


    public void selectMenu(String menu) {
        List<WebElement> listOfMenuElements = driver.findElements(By.xpath("//div[@class='header-menu']/ul[@class='top-menu notmobile']/li/a"));
        for (WebElement element : listOfMenuElements) {
            System.out.println(element.getText());

            if (element.getText().trim().equalsIgnoreCase(menu)) {
                element.click();
                break;
            }
        }
    }

    @Test
    public void verifyPageNavigation() {
        selectMenu("Electronics");

    }


}
