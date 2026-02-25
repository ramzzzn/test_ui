package ru.stepup.testui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PobedaStartPage {
    WebDriver driver;

    @FindBy(css = "header canvas")
    WebElement logoPobeda;

    @FindBy(css = "a[href='/information']")
    WebElement informationPopup;

    @FindBy(xpath = "//a[contains(text(), 'Подготовка к полёту')]")
    WebElement prepareForFlightHeader;

    @FindBy(xpath = "//a[contains(text(), 'Полезная информация')]")
    WebElement usefulInfoHeader;

    @FindBy(xpath = "//a[contains(text(), 'О компании')]")
    WebElement aboutCompanyHeader;

    @FindBy(css = "div[class*='root-container']")
    WebElement searchUnit;

    @FindBy(css = "input[placeholder='Откуда']")
    WebElement departureCityInput;

    @FindBy(css = "input[placeholder='Куда']")
    WebElement arrivalCityInput;

    @FindBy(xpath = "//input[@placeholder='Туда']")
    WebElement departureDateInput;

    @FindBy(css = "input[placeholder='Обратно']")
    WebElement returningDateInput;

    @FindBy(xpath = "//button[.//text()='Поиск']")
    WebElement searchButton;

    public PobedaStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isLogoDisplayed() {
        return logoPobeda.isDisplayed();
    }

    public String getTitleText() {
        return driver.getTitle();
    }

    public void hoverOverInformationPopup() {
        Actions actions = new Actions(driver);
        actions.moveToElement(informationPopup).perform();
    }

    public void scrollToSearchUnit() {
        Actions actions = new Actions(driver);
        actions.moveToElement(searchUnit).perform();
    }

    public void clearSearchUnitInput(WebElement inputField) {
        inputField.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", inputField);
    }

    private void selectItem(String item) {
        WebElement menuItem = driver.findElement(
                By.xpath("//div[@role='menuitem']//div[contains(text(), '" + item + "')]"));
        Actions actions = new Actions(driver);
        actions.moveToElement(menuItem).sendKeys(Keys.ENTER).perform();
    }

    public void setDepartureCity(String departureCity) {
        clearSearchUnitInput(departureCityInput);
        departureCityInput.sendKeys(departureCity);
        selectItem(departureCity);
    }

    public void setArrivalCity(String arrivalCity) {
        clearSearchUnitInput(arrivalCityInput);
        arrivalCityInput.sendKeys(arrivalCity);
        selectItem(arrivalCity);
    }

    public void submitSearchButton() {
        searchButton.click();
    }

    public String getDepartureDateInputBorderColor() {
        WebElement parentElement = departureDateInput.findElement(By.xpath(".."));
        return parentElement.getCssValue("border-color");
    }

    public boolean isPrepareForFlightHeaderVisible() {
        return prepareForFlightHeader.isDisplayed();
    }

    public boolean isUsefulInfoHeaderVisible() {
        return usefulInfoHeader.isDisplayed();
    }

    public boolean isAboutCompanyHeaderVisible() {
        return aboutCompanyHeader.isDisplayed();
    }

    public boolean isDepartureCityInputVisible() {
        return departureCityInput.isDisplayed();
    }

    public boolean isArrivalCityInputVisible() {
        return arrivalCityInput.isDisplayed();
    }

    public boolean isDepartureDateInputVisible() {
        return departureDateInput.isDisplayed();
    }

    public boolean isReturningDateInputVisible() {
        return returningDateInput.isDisplayed();
    }
}
