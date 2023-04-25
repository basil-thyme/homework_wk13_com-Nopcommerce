package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utility.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestSuite extends Utility {

    @Before
    public void setUp() {
        String baseUrl = "https://demo.nopcommerce.com/";
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    By computers = By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Computers']");
    By desktop = By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']");
    By addToCart = By.xpath("//div[@class='details']//button[@type='button']");
    By buildYourOwnComputerTitleLocator = By.xpath("//h2[@class='product-title']//a[normalize-space()='Build your own computer']");
    By processorDropdownLocator = By.cssSelector("#product_attribute_1");
    By sortDesktopsPage = By.xpath("//select[@id='products-orderby']");

    /**
     * 1.Test name verifyProductArrangeInAlphaBaticalOrder()
     * 1.1 Click on Computer Menu.
     * 1.2 Click on Desktop
     * 1.3 Select Sort By position "Name: Z to A"
     * 1.4 Verify the Product will arrange in Descending order.
     * 2. Test
     */

    @Test
    public void verifyProductArrangeInAlphabaticalOrder() throws InterruptedException {
        clickOnElement(computers);
        clickOnElement(desktop);

        selectElementFromDropDownByIndex(sortDesktopsPage, 2);
        Thread.sleep(2000);
        ArrayList<String> actualList = new ArrayList<>();

        List<WebElement> listOfElement = driver.findElements(By.xpath("//h2[@class='product-title']/a"));
        for (WebElement element : listOfElement) {
            actualList.add(element.getText());
        }
        System.out.println(actualList);
        ArrayList<String> expectedList = new ArrayList<>();
        for (WebElement element : listOfElement) {
            expectedList.add(element.getText());
        }
        Collections.sort(expectedList);
        Collections.reverse(expectedList);

        System.out.println(expectedList);
        Assert.assertEquals("Not matching", expectedList, actualList);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        clickOnElement(computers);
        clickOnElement(desktop);
        WebElement dropDownElement = driver.findElement(By.xpath("//select[@id='products-orderby']"));
        Select select = new Select(dropDownElement);
        select.selectByIndex(1);
        Thread.sleep(2000);
        // 2.4 Click on "Add To Cart"
        clickOnElement(addToCart);

        // 2.5 Verify the Text "Build your own computer"
        By buildYourOwnComputerTitleLocator = By.xpath("//h1[normalize-space()='Build your own computer']");
        assertToVerify(buildYourOwnComputerTitleLocator, "Build your own computer");

        // 2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        By processorDropdownLocator = By.cssSelector("#product_attribute_1");
        selectElementFromDropDownByIndex(processorDropdownLocator, 2);

        // 2.7.Select "8GB [+$60.00]" using Select class.
        By ramDropdownLocator = By.cssSelector("#product_attribute_2");
        selectElementFromDropDownByIndex(ramDropdownLocator, 3);

        // 2.8 Select HDD radio "400 GB [+$100.00]"
        By hddRadioButtonLocator = By.cssSelector("#product_attribute_3_7");
        clickOnElement(hddRadioButtonLocator);

        // 2.9 Select OS radio "Vista Premium [+$60.00]"
        By osRadioButtonLocator = By.cssSelector("#product_attribute_4_9");
        clickOnElement(osRadioButtonLocator);

        // 2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        By msOfficeCheckBoxLocator = By.cssSelector("#product_attribute_5_10");
        WebElement element = driver.findElement(msOfficeCheckBoxLocator);
        if (!element.isSelected()) {
            clickOnElement(msOfficeCheckBoxLocator);
        }

        By totalCommanderCheckBoxLocator = By.cssSelector("#product_attribute_5_12");
        clickOnElement(totalCommanderCheckBoxLocator);

        // 2.11 Verify the price "$1,475.00"
        By priceTotalLocator = By.cssSelector("#price-value-1");

        assertToVerify(priceTotalLocator, "$1,315.00");

        // 2.12 Click on "ADD TO CARD" Button.
        By addToCartButton1Locator = By.xpath("//button[@id='add-to-cart-button-1']");
        Thread.sleep(2000);
        clickOnElement(addToCartButton1Locator);

        // 2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        By messageConfirmationLocator = By.cssSelector(".content");
        assertToVerify(messageConfirmationLocator, "The product has been added to your shopping cart");

        // After that close the bar clicking on the cross button.
        By crossToCloseLocator = By.xpath("//span[@title='Close']");
        clickOnElement(crossToCloseLocator);

        // 2.14 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        By shoppingCartLocator = By.cssSelector(".cart-label");
        hoverMouseToElement(shoppingCartLocator);

        By goToCartButtonLocator = By.cssSelector(".button-1.cart-button");
        clickOnElement(goToCartButtonLocator);

        // 2.15 Verify the message "Shopping cart"
        By shoppingCartTitleLocator = By.xpath("//h1[normalize-space()='Shopping cart']");
        assertToVerify(shoppingCartTitleLocator, "Shopping cart");

        // 2.16 Change the Qty to "2" and Click on "Update shopping cart"
        By qtyFieldLocator = By.xpath("//input[@class='qty-input']");
        //WebElement qtyFieldElement = driver.findElement(qtyFieldLocator);
        //qtyFieldElement.clear();
        clearValueFromElement(qtyFieldLocator);
        Thread.sleep(1000);
        sendTextToElement(qtyFieldLocator, "2");

        By updateShoppingCartButtonLocator = By.cssSelector("#updatecart");
        clickOnElement(updateShoppingCartButtonLocator);

        // 2.17 Verify the Total"$2,950.00"
        By totalPriceLocator = By.xpath("//tr[@class='order-total']//td[@class='cart-total-right']/span");
        assertToVerify(totalPriceLocator, "$2,980.00");

        // 2.18 click on checkbox “I agree with the terms of service”
        By termsOfServiceCheckBoxLocator = By.cssSelector("#termsofservice");
        clickOnElement(termsOfServiceCheckBoxLocator);

        // 2.19 Click on “CHECKOUT”
        By checkoutButtonLocator = By.cssSelector("#checkout");
        clickOnElement(checkoutButtonLocator);

        // 2.20 Verify the Text “Welcome, Please Sign In!”
        By welcomeSignInPageTitleLocator = By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']");
        assertToVerify(welcomeSignInPageTitleLocator, "Welcome, Please Sign In!");

        // 2.21 Click on “CHECKOUT AS GUEST”Tab
        By checkoutAsGuestButtonLocator = By.cssSelector(".button-1.checkout-as-guest-button");
        clickOnElement(checkoutAsGuestButtonLocator);

        // 2.22 Fill the all mandatory field
        By firstNameFieldLocator = By.cssSelector("#BillingNewAddress_FirstName");
        sendTextToElement(firstNameFieldLocator, "John");

        // last name
        By lastNameFieldLocator = By.cssSelector("#BillingNewAddress_LastName");
        sendTextToElement(lastNameFieldLocator, "Smith");

        // email
        By emailFieldLocator = By.cssSelector("#BillingNewAddress_Email");
        sendTextToElement(emailFieldLocator, "XYZ@gmail.com");

        // country drop down
        By countryDropDownLocator = By.cssSelector("#BillingNewAddress_CountryId");
        selectElementFromDropDownByIndex(countryDropDownLocator, 2);

        // city
        By cityFieldLocator = By.cssSelector("#BillingNewAddress_City");
        sendTextToElement(cityFieldLocator, "London");

        // address 1
        By address1FieldLocator = By.cssSelector("#BillingNewAddress_Address1");
        sendTextToElement(address1FieldLocator, "Random Street");

        // post code
        By postCodeFieldLocator = By.cssSelector("#BillingNewAddress_ZipPostalCode");
        sendTextToElement(postCodeFieldLocator, "WES 6RT");

        // phone number
        By phoneNumberFieldLocator = By.cssSelector("#BillingNewAddress_PhoneNumber");
        sendTextToElement(phoneNumberFieldLocator, "0777777777");

        // Click on “CONTINUE”
        By continueButtonLocator = By.name("save");
        clickOnElement(continueButtonLocator);

        //Now filling Shipping Method

        // 2.24 Click on Radio Button “Next Day Air($0.00)”
        By nextDayAirRadioLocator = By.cssSelector("#shippingoption_1");
        clickOnElement(nextDayAirRadioLocator);

        // 2.25 Click on “CONTINUE”
        By continueButtonAfterShippingLocator = By.cssSelector(".button-1.shipping-method-next-step-button");
        clickOnElement(continueButtonAfterShippingLocator);

        // 2.26 Select Radio Button “Credit Card”
        By creditCartRadioLocator = By.cssSelector("#paymentmethod_1");
        clickOnElement(creditCartRadioLocator);

        // click on continue button
        By continueButtonPaymentMethodLocator = By.cssSelector(".button-1.payment-method-next-step-button");
        clickOnElement(continueButtonPaymentMethodLocator);

        // 2.28 Fill all the details
        // Selecting credit card details
        // 2.27 Select “Master card” From Select credit card dropdown
        By creditCardDropdownLocator = By.id("CreditCardType");
        selectElementFromDropDownByIndex(creditCardDropdownLocator, 2);

        // cardholder name
        By cardHolderNameFieldLocator = By.id("CardholderName");
        sendTextToElement(cardHolderNameFieldLocator, "Mr Smith");

        // enter card number : 5425233430109903
        // expiry = 01/26
        // csv = 123
        // name = Mr Smith
        // reference website: https://support.bluesnap.com/docs/test-credit-card-numbers

        // card number
        By cardNumberFieldLocator = By.id("CardNumber");
        sendTextFromElement(cardNumberFieldLocator, "5425233430109903");

        // expiry moth drop down
        By expireMonthDropdownLocator = By.id("ExpireMonth");
        selectElementFromDropDownByIndex(expireMonthDropdownLocator, 1);

        // expiry year drop down
        By expireYearDropdownLocator = By.id("ExpireYear");
        selectElementFromDropDownByIndex(expireYearDropdownLocator, 3);

        // card code
        By cardCodeFieldLocator = By.xpath("//input[@data-val-regex-pattern='^[0-9]{3,4}$']");
        sendTextFromElement(cardCodeFieldLocator, "123");

        // 2.29 Click on “CONTINUE”
        By continueButtonAfterCreditcardInfoLocator = By.cssSelector(".button-1.payment-info-next-step-button");
        clickOnElement(continueButtonAfterCreditcardInfoLocator);

        // Confirm Order Section
        // verifying payment method is credit card
        // 2.30 Verify “Payment Method” is “Credit Card”
        By paymentMethodTextLocator = By.xpath("//li[@class='payment-method']//span[2]");
        assertToVerify(paymentMethodTextLocator, "Credit Card");

        // 2.32 Verify “Shipping Method” is “Next Day Air”
        By shippingMethodTextLocator = By.xpath("//li[@class='shipping-method']//span[2]");
        assertToVerify(shippingMethodTextLocator, "Next Day Air");

        // 2.33 Verify Total is “$2,980.00”
        By totalPrice1Locator = By.xpath("//tr[@class='order-total']/td[@class='cart-total-right']/span");
        assertToVerify(totalPrice1Locator, "$2,980.00");

        // 2.34 Click on “CONFIRM”
        By confirmButtonLocator = By.cssSelector(".button-1.confirm-order-next-step-button");
        clickOnElement(confirmButtonLocator);

        // thank you page title and order completion messages

        // 2.35 Verify the Text “Thank You”
        By thankYouTitleLocator = By.xpath("//h1[normalize-space()='Thank you']");
        assertToVerify(thankYouTitleLocator, "Thank you");


        // 2.36 Verify the message “Your order has been successfully processed!”
        By successfullMessageLocator = By.xpath("//strong[normalize-space()='Your order has been successfully processed!']");
        assertToVerify(successfullMessageLocator, "Your order has been successfully processed!");

        // 2.37 Click on “CONTINUE”
        By continueButtonCompletedOrderLocator = By.cssSelector(".button-1.order-completed-continue-button");
        clickOnElement(continueButtonCompletedOrderLocator);

        // welcome to store title
        // 2.37 Verify the text “Welcome to our store
        By welcomeToStoreLocator = By.xpath("//h2[normalize-space()='Welcome to our store']");
        assertToVerify(welcomeToStoreLocator,"Welcome to our store");

    }

}
