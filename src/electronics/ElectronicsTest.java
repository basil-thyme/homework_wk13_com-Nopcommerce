package electronics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utility.Utility;

import java.util.List;

public class ElectronicsTest extends Utility {
    static String baseUrl = "https://demo.nopcommerce.com/";
    static String email;
    static String password = "Password1";

    /**
     * Test name verifyUserShouldNavigateToCellPhonesPageSuccessfully()
     * 1.1 Mouse Hover on “Electronics” Tab
     * 1.2 Mouse Hover on “Cell phones” and click
     * 1.3 Verify the text “Cell phones”
     */

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        //1.1 Mouse Hover on “Electronics Tab"
        By electronicMenuLocator = By.linkText("Electronics");
        hoverMouseToElement(electronicMenuLocator);

        //1.2 Mouse Hover on “Cell phones” and click
        By cellPhonesSubMenuLocator = By.linkText("Cell phones");
        hoverMouseToElementAndClick(cellPhonesSubMenuLocator);

        // 1.3 Verify the text “Cell phones”
        By cellPhoneTitleLocator = By.xpath("//h1[normalize-space()='Cell phones']");
        assertToVerify(cellPhoneTitleLocator, "Cell phones");

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        /*2.1 Mouse Hover on “Electronics”Tab
        2.2 Mouse Hover on “Cell phones” and click
        2.3 Verify the text “Cell phones”*//**//*
         */
        By electronicMenuLocator = By.linkText("Electronics");
        hoverMouseToElement(electronicMenuLocator);

        By cellPhonesSubMenuLocator = By.linkText("Cell phones");
        hoverMouseToElementAndClick(cellPhonesSubMenuLocator);

        By cellPhoneTitleLocator = By.xpath("//h1[normalize-space()='Cell phones']");
        assertToVerify(cellPhoneTitleLocator, "Cell phones");

        //2.4 Click on List View Tab
        By listViewToggleLocator = By.cssSelector(".viewmode-icon.list");
        clickOnElement(listViewToggleLocator);

        //2.5 Click on product name “Nokia Lumia 1020” link
        // By nokiaProductNameLocator = By.xpath("//a[contains(text(),'Nokia Lumia 1020')]");
        //  By nokiaProductNameLocator = By.xpath("//a[normalize-space()='Nokia Lumia 1020']");
        Thread.sleep(2000);
        List<WebElement> mobileTitlesList = driver.findElements(By.xpath("//h2[@class='product-title']/a"));

        for (WebElement element : mobileTitlesList) {
            if (element.getText().contains("Nokia")) {
                element.click();
                break;
            }
        }

        //2.7 Verify the price “$349.00”
        By priceLocator = By.id("price-value-20");
        assertToVerify(priceLocator, "$349.00");

        //2.8 Change quantity to 2
        By qtyFieldLocator = By.id("product_enteredQuantity_20");
        WebElement element = driver.findElement(qtyFieldLocator);
        element.clear();
        sendTextFromElement(qtyFieldLocator, "2");

        //2.9 Click on “ADD TO CART” tab
        By addToCartButtonLocator = By.id("add-to-cart-button-20");
        clickOnElement(addToCartButtonLocator);

        /** 2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
         // pace Thread.sleep here since it take time to load this message*/
        By messageLocator = By.cssSelector(".content");
        WebElement messageElement = driver.findElement(messageLocator);
        assertToVerify(messageLocator, "The product has been added to your shopping cart");

        /** After that close the bar clicking on the cross button.
         //        After that close the bar clicking on the cross button.*/
        By crossToCloseLocator = By.xpath("//span[@title='Close']");
        clickOnElement(crossToCloseLocator);

        //         * 2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        By shoppingCartLocator = By.cssSelector(".cart-label");
        hoverMouseToElement(shoppingCartLocator);

        By goToCartButtonLocator = By.cssSelector(".button-1.cart-button");
        clickOnElement(goToCartButtonLocator);

//         * 2.12 Verify the message "Shopping cart"
        By shoppingCartTitleLocator = By.xpath("//h1[normalize-space()='Shopping cart']");
        assertToVerify(shoppingCartTitleLocator, "Shopping cart");

//         * 2.13 Verify the quantity is 2
        By qtyField1Locator = By.className("qty-input");
        assertToVerifyByAttribute(qtyField1Locator, "value", "2");

        //2.14 Verify the Total $698.00
        By totalPriceLocator = By.xpath("//tr[@class='order-total']//td[@class='cart-total-right']/span");
        assertToVerify(totalPriceLocator, "$698.00");

//         * 2.15 click on checkbox “I agree with the terms of service”
        By termsOfServiceCheckBoxLocator = By.cssSelector("#termsofservice");
        clickOnElement(termsOfServiceCheckBoxLocator);

//         * 2.16 Click on “CHECKOUT”
        By checkoutButtonLocator = By.cssSelector("#checkout");
        clickOnElement(checkoutButtonLocator);

//         * 2.17 Verify the Text “Welcome, Please Sign In!”
        By welcomeSignInPageTitleLocator = By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']");
        WebElement welcomeSignInPageTitleElement = driver.findElement(welcomeSignInPageTitleLocator);
        assertToVerify(welcomeSignInPageTitleLocator, "Welcome, Please Sign In!");

        //         * 2.18 Click on “REGISTER” button
        By registerButtonLocator = By.cssSelector(".button-1.register-button");
        clickOnElement(registerButtonLocator);

//         * 2.19 Verify the text “Register”
        By registerTitleLocator = By.xpath("//h1[normalize-space()='Register']");
        assertToVerify(registerTitleLocator, "Register");

//         * 2.20 Fill the mandatory fields

        // first name
        By firstNameFieldLocator = By.id("FirstName");
        sendTextFromElement(firstNameFieldLocator, "Peter");

        // last name
        By lastNameFieldLocator = By.id("LastName");
        sendTextFromElement(lastNameFieldLocator, "Smith");

        // email
        By emailFieldLocator = By.id("Email");
        //"xyz_999@example.com"
        String e = doGetRandomString(5) + "_999@example.com";
        ElectronicsTest.email = e;
        sendTextFromElement(emailFieldLocator, ElectronicsTest.email);

        // password
        By passwordFieldLocator = By.id("Password");
        sendTextFromElement(passwordFieldLocator, ElectronicsTest.password);

        // confirm password
        By confirmPasswordFieldLocator = By.id("ConfirmPassword");
        sendTextFromElement(confirmPasswordFieldLocator, ElectronicsTest.password);

        //2.21 Click on “REGISTER” Button
        By registerButton1Locator = By.id("register-button");
        clickOnElement(registerButton1Locator);

        //2.22 Verify the message “Your registration completed”
        By registerCompletedMessageLocator = By.xpath("//div[@class='result']");
        WebElement registerCompletedMessageElement = driver.findElement(registerCompletedMessageLocator);

        // * 2.23 Click on “CONTINUE” tab
        By continueButtonOnRegisterCompletedLocator = By.cssSelector(".button-1.register-continue-button");
        clickOnElement(continueButtonOnRegisterCompletedLocator);

//         * 2.24 Verify the text “Shopping cart”
        By shoppingCartTitle1Locator = By.xpath("//h1[normalize-space()='Shopping cart']");
        assertToVerify(shoppingCartTitle1Locator, "Shopping cart");



        // click on Log in
        By logInLinkLocator = By.linkText("Log in");
        clickOnElement(logInLinkLocator);

        // Email field
        By emailField1Locator = By.cssSelector("#Email");
        sendTextFromElement(emailField1Locator, ElectronicsTest.email);

        // password field
        By passwordField1Locator = By.cssSelector("#Password");
        sendTextFromElement(passwordField1Locator, ElectronicsTest.password);

        By loginButton = By.cssSelector(".button-1.login-button");
        clickOnElement(loginButton);

//         * 2.25 click on checkbox “I agree with the terms of service”
        By termsOfServiceCheckBoxLocator1 = By.cssSelector("#termsofservice");
        clickOnElement(termsOfServiceCheckBoxLocator1);

//         * 2.26 Click on “CHECKOUT”
        By checkoutButtonLocator1 = By.cssSelector("#checkout");
        clickOnElement(checkoutButtonLocator1);

        // country drop down
        By countryDropDownLocator = By.cssSelector("#BillingNewAddress_CountryId");
        selectElementFromDropDownByIndex(countryDropDownLocator, 11);

        // city
        By cityFieldLocator = By.cssSelector("#BillingNewAddress_City");
        sendTextFromElement(cityFieldLocator, "London");

        // address 1
        By address1FieldLocator = By.cssSelector("#BillingNewAddress_Address1");
        sendTextFromElement(address1FieldLocator, "Random Street");

        // post code
        By postCodeFieldLocator = By.cssSelector("#BillingNewAddress_ZipPostalCode");
        sendTextFromElement(postCodeFieldLocator, "AS3BE3");

        // phone number
        By phoneNumberFieldLocator = By.cssSelector("#BillingNewAddress_PhoneNumber");
        sendTextToElement(phoneNumberFieldLocator, "0712458963");

        /**
         * mandatory fields are completed
         */


//         * 2.28 Click on “CONTINUE”
        By continueButtonLocator = By.name("save");
        clickOnElement(continueButtonLocator);

        //         * 2.29 Click on Radio Button “2nd Day Air ($0.00)”
        By secondDayAirRadioLocator = By.cssSelector("#shippingoption_2");
        clickOnElement(secondDayAirRadioLocator);

//         * 2.30 Click on “CONTINUE”
        By continueButtonAfterShippingLocator = By.cssSelector(".button-1.shipping-method-next-step-button");
        clickOnElement(continueButtonAfterShippingLocator);

//         * 2.31 Select Radio Button “Credit Card”
        By creditCartRadioLocator = By.cssSelector("#paymentmethod_1");
        clickOnElement(creditCartRadioLocator);

        // click on continue button
        By continueButtonPaymentMethodLocator = By.cssSelector(".button-1.payment-method-next-step-button");
        clickOnElement(continueButtonPaymentMethodLocator);

//         * 2.32 Select “Visa” From Select credit card dropdown
        By creditCardDropdownLocator = By.id("CreditCardType");
        selectElementFromDropDownByVisibleText(creditCardDropdownLocator, "Visa");

//         * 2.33 Fill all the details

        // enter card number : 4263982640269299
        // expiry = 01/26
        // csv = 123
        // name = Mr Smith
        // reference website: https://support.bluesnap.com/docs/test-credit-card-numbers

        // cardholder name
        By cardHolderNameFieldLocator = By.id("CardholderName");
        sendTextToElement(cardHolderNameFieldLocator, "Mr Smith");

        // card number
        By cardNumberFieldLocator = By.id("CardNumber");
        sendTextToElement(cardNumberFieldLocator, "4263982640269299");

        // expiry moth drop down
        By expireMonthDropdownLocator = By.id("ExpireMonth");
        selectElementFromDropDownByIndex(expireMonthDropdownLocator, 1);

        // expiry year drop down
        By expireYearDropdownLocator = By.id("ExpireYear");
        selectElementFromDropDownByIndex(expireYearDropdownLocator, 3);

        // card code
        By cardCodeFieldLocator = By.id("CardCode");
        sendTextToElement(cardCodeFieldLocator, "123");

//         * 2.34 Click on “CONTINUE”
        By continueButtonAfterCreditcardInfoLocator = By.cssSelector(".button-1.payment-info-next-step-button");
        clickOnElement(continueButtonAfterCreditcardInfoLocator);

//         * 2.35 Verify “Payment Method” is “Credit Card”
        By paymentMethodTextLocator = By.xpath("//li[@class='payment-method']//span[2]");
        assertToVerify(paymentMethodTextLocator, "Credit Card");

//         * 2.36 Verify “Shipping Method” is “2nd Day Air”
        By shippingMethodTextLocator = By.xpath("//li[@class='shipping-method']//span[2]");
        assertToVerify(shippingMethodTextLocator, "2nd Day Air");

//         * 2.37 Verify Total is “$698.00”
        By totalPrice1Locator = By.xpath("//tr[@class='order-total']/td[@class='cart-total-right']/span");
        assertToVerify(totalPrice1Locator, "$698.00");

//         * 2.38 Click on “CONFIRM”
        By confirmButtonLocator = By.cssSelector(".button-1.confirm-order-next-step-button");
        clickOnElement(confirmButtonLocator);

//         * 2.39 Verify the Text “Thank You”
        By thankYouTitleLocator = By.xpath("//h1[normalize-space()='Thank you']");
        assertToVerify(thankYouTitleLocator, "Thank you");

//         * 2.40 Verify the message “Your order has been successfully processed!”
        By successfullMessageLocator = By.xpath("//strong[normalize-space()='Your order has been successfully processed!']");
        assertToVerify(successfullMessageLocator, "Your order has been successfully processed!");

//         * 2.41 Click on “CONTINUE”
        By continueButtonCompletedOrderLocator = By.cssSelector(".button-1.order-completed-continue-button");
        clickOnElement(continueButtonCompletedOrderLocator);

//         * 2.42 Verify the text “Welcome to our store”
        By welcomeToStoreLocator = By.xpath("//h2[normalize-space()='Welcome to our store']");
        assertToVerify(welcomeToStoreLocator, "Welcome to our store");

//         * 2.43 Click on “Logout” link
        By logOutLinkLocator = By.linkText("Log out");
        clickOnElement(logOutLinkLocator);

//         * 2.44 Verify the URL is “https://demo.nopcommerce.com

        String actual = driver.getCurrentUrl();


    }


}
