package ru.stepup.testui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchOrderPage {
    WebDriver driver;

    @FindBy(css = "input[placeholder='Номер брони или билета']")
    WebElement orderNumberInput;

    @FindBy(css = "input[placeholder='Фамилия']")
    WebElement clientLastNameInput;

    @FindBy(xpath = "//button[.//text()='Найти заказ']")
    WebElement searchButton;

    @FindBy(xpath = "//div[@class='customCheckbox']//span")
    WebElement checkBoxSearchOrderAgree;

    @FindBy(css = "div[class='message_error']")
    WebElement errorMessage;


    public SearchOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public void clickCheckBoxSearchOrderAgree() {
        checkBoxSearchOrderAgree.click();
    }

    public void submitSearchBooking() {
        clickCheckBoxSearchOrderAgree();
        clickSearchButton();
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public boolean isOrderNumberInputVisible() {
        return orderNumberInput.isDisplayed();
    }

    public boolean isClientLastNameInputVisible() {
        return clientLastNameInput.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        return searchButton.isDisplayed();
    }

    public boolean isErrorMessageVisible() {
        return errorMessage.isDisplayed();
    }
}
