package ru.stepup.testui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingManagmentPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "input[placeholder='Номер бронирования или билета']")
    WebElement orderNumberInput;

    @FindBy(css = "input[placeholder='Фамилия клиента']")
    WebElement clientLastNameInput;

    @FindBy(xpath = "//button[.//text()='Поиск']")
    WebElement searchButton;

    public BookingManagmentPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void setOrderNumber(String orderNumber) {
        orderNumberInput.sendKeys(orderNumber);
    }

    public void setClientLastName(String clientLastName) {
        clientLastNameInput.sendKeys(clientLastName);
    }

    public SearchOrderPage clickSearchButton() {
        String oldTab = driver.getWindowHandle();
        searchButton.click();
        // Переключаем driver на новую вкладку
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(oldTab)) {
                driver.switchTo().window(handle);
            }
        }
        return new SearchOrderPage(driver);
    }

    public boolean isOrderNumberInputVisible() {
        wait.until(ExpectedConditions.visibilityOf(orderNumberInput));
        return orderNumberInput.isDisplayed();
    }

    public boolean isClientLastNameInputVisible() {
        wait.until(ExpectedConditions.visibilityOf(orderNumberInput));
        return clientLastNameInput.isDisplayed();
    }

    public boolean isSearchButtonVisible() {
        wait.until(ExpectedConditions.visibilityOf(orderNumberInput));
        return searchButton.isDisplayed();
    }

    public SearchOrderPage searchBooking(String orderNumber, String clientLastName) {
        setOrderNumber(orderNumber);
        setClientLastName(clientLastName);
        return clickSearchButton();
    }
}
