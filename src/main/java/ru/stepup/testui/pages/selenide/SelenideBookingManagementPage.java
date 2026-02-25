package ru.stepup.testui.pages.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;


public class SelenideBookingManagementPage {

    public SelenideBookingManagementPage() {
    }

    public SelenideElement orderNumberInput = $("input[placeholder='Номер бронирования или билета']");
    public SelenideElement clientLastNameInput = $("input[placeholder='Фамилия клиента']");
    public SelenideElement searchButton = $(By.xpath("//button[.//text()='Поиск']"));


    public SelenideBookingManagementPage checkOrderNumberInputIsVisible() {
        orderNumberInput.shouldBe(visible);
        return this;
    }

    public SelenideBookingManagementPage checkClientLastNameInputVisible() {
        clientLastNameInput.shouldBe(visible);
        return this;
    }

    public SelenideBookingManagementPage checkSearchButtonIsVisible() {
        searchButton.shouldBe(visible);
        return this;
    }

    public SelenideBookingManagementPage checkBookingManagementPageElements() {
        checkOrderNumberInputIsVisible()
                .checkClientLastNameInputVisible()
                .checkSearchButtonIsVisible();
        return this;
    }

    public SelenideBookingManagementPage setBookingNumber(String orderNumber) {
        orderNumberInput.setValue(orderNumber);
        return this;
    }

    public SelenideBookingManagementPage setClientLastName(String lastName) {
        clientLastNameInput.setValue(lastName);
        return this;
    }

    public SelenideSearchBookingPage clickSearchButton() {
        searchButton.click();
        switchTo().window(1);
        return new SelenideSearchBookingPage();
    }

    public SelenideSearchBookingPage searchBooking(String orderNumber, String clientLastName) {
        setBookingNumber(orderNumber)
                .setClientLastName(clientLastName);
        return clickSearchButton();
    }
}
