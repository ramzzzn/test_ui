package ru.stepup.testui.pages;

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

    public boolean isPrepareForFlightHeaderVisible() {
        return prepareForFlightHeader.isDisplayed();
    }

    public boolean isUsefulInfoHeaderVisible() {
        return usefulInfoHeader.isDisplayed();
    }

    public boolean isAboutCompanyHeaderVisible() {
        return aboutCompanyHeader.isDisplayed();
    }
}
