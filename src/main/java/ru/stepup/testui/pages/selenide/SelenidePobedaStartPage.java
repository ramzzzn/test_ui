package ru.stepup.testui.pages.selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;
import static com.codeborne.selenide.WebDriverConditions.url;


public class SelenidePobedaStartPage {
    public static final String BASE_URL = "https://www.flypobeda.ru/";

    public SelenidePobedaStartPage() {
        Configuration.browserSize = "1920x1080";
        open(BASE_URL);
        webdriver().shouldHave(url(BASE_URL));
    }

    public SelenideElement logoPobeda = $("header canvas");
    public SelenideElement informationPopup = $("a[href='/information']");
    public SelenideElement searchTicketUnit = $("div[class*='root-container']");
    public SelenideElement departureCityInput = $("input[placeholder='Откуда']");
    public SelenideElement arrivalCityInput = $("input[placeholder='Куда']");
    public SelenideElement departureDateInput = $("input[placeholder='Туда']");
    public SelenideElement returnDateInput = $("input[placeholder='Обратно']");
    public SelenideElement searchButton = $(By.xpath("//button[.//text()='Поиск']"));
    public SelenideElement bookingManagementPageLink = $(By.xpath("//div[not(@class)]/a[contains(text(), 'Управление бронированием')]"));
    public SelenideElement prepareForFlightHeader = $(By.xpath("//a[contains(text(), 'Подготовка к полёту')]"));
    public SelenideElement usefulInfoHeader = $(By.xpath("//a[contains(text(), 'Полезная информация')]"));
    public SelenideElement aboutCompanyHeader = $(By.xpath("//a[contains(text(), 'О компании')]"));


    public SelenidePobedaStartPage checkLogoIsVisible() {
        logoPobeda.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkPrepareForFlightHeaderVisible() {
        prepareForFlightHeader.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkUsefulInfoHeaderIsVisible() {
        usefulInfoHeader.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkAboutCompanyHeaderIsVisible() {
        aboutCompanyHeader.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkDepartureCityInputIsVisible() {
        departureCityInput.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkArrivalCityInputIsVisible() {
        arrivalCityInput.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkDepartureDateInputIsVisible() {
        departureDateInput.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkReturningDateInputIsVisible() {
        returnDateInput.shouldBe(visible);
        return this;
    }

    public SelenidePobedaStartPage checkPobedaStartPageIsOpened() {
        webdriver().shouldHave(title("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты " +
                "на самолёт, прямые и трансферные рейсы с пересадками"));
        checkLogoIsVisible();
        return this;
    }

    public SelenidePobedaStartPage hoverOverInformationPopup() {
        informationPopup.hover();
        return this;
    }

    public SelenidePobedaStartPage checkInformationPopupHeaders() {
        checkPrepareForFlightHeaderVisible()
                .checkUsefulInfoHeaderIsVisible()
                .checkAboutCompanyHeaderIsVisible();
        return this;
    }

    public SelenidePobedaStartPage scrollToSearchUnit() {
        searchTicketUnit.scrollIntoView(true);
        return this;
    }

    public SelenidePobedaStartPage checkSearchTicketUnitInputFields() {
        checkDepartureCityInputIsVisible()
                .checkArrivalCityInputIsVisible()
                .checkDepartureDateInputIsVisible()
                .checkReturningDateInputIsVisible();
        return this;
    }

    public SelenidePobedaStartPage selectItemFromDrop(String itemText) {
        $$(By.xpath("//div[@role='menuitem']"))
                .findBy(text(itemText))
                .hover()
                .pressEnter();
        return this;
    }

    public void clearInput(SelenideElement element) {
        element.click();
        executeJavaScript("arguments[0].value='';", element);
    }

    public SelenidePobedaStartPage setDepartureCity(String departureCity) {
        clearInput(departureCityInput);
        departureCityInput.setValue(departureCity);
        selectItemFromDrop(departureCity);
        return this;
    }

    public SelenidePobedaStartPage setArrivalCity(String arrivalCity) {
        clearInput(arrivalCityInput);
        arrivalCityInput.setValue(arrivalCity);
        selectItemFromDrop(arrivalCity);
        return this;
    }

    public SelenidePobedaStartPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public SelenidePobedaStartPage checkDepartureDateInputBorderColor(String expectedValue) {
        SelenideElement parentElement = departureDateInput.parent();
        parentElement.shouldHave(cssValue("border-color", expectedValue));
        return this;
    }

    public SelenideBookingManagementPage openBookingManagementPage() {
        bookingManagementPageLink.scrollIntoView(true).click();
        return new SelenideBookingManagementPage();
    }
}
