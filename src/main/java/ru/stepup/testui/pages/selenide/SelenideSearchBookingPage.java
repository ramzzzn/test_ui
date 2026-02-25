package ru.stepup.testui.pages.selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class SelenideSearchBookingPage {

    public SelenideSearchBookingPage() {
    }

    public SelenideElement orderNumberInput = $("input[placeholder='Номер брони или билета']");
    public SelenideElement clientLastNameInput = $("input[placeholder='Фамилия']");
    public SelenideElement errorMessage = $("div[class='message_error']");
    public SelenideElement searchButton = $(By.xpath("//button[.//text()='Найти заказ']"));
    public SelenideElement searchOrderAgreeCheckbox = $(By.xpath("//div[@class='customCheckbox']//span"));


    public SelenideSearchBookingPage checkOrderNumberInputIsVisible() {
        orderNumberInput.shouldBe(visible);
        return this;
    }

    public SelenideSearchBookingPage checkClientLastNameInputVisible() {
        clientLastNameInput.shouldBe(visible);
        return this;
    }

    public SelenideSearchBookingPage checkSearchButtonIsVisible() {
        searchButton.shouldBe(visible);
        return this;
    }

    public SelenideSearchBookingPage checkSearchBookingPageElements() {
        checkOrderNumberInputIsVisible();
        checkClientLastNameInputVisible();
        checkSearchButtonIsVisible();
        return this;
    }

    public SelenideSearchBookingPage clickSearchOrderAgreeCheckbox() {
        searchOrderAgreeCheckbox.click();
        return this;
    }

    public SelenideSearchBookingPage clickSearchButton() {
        searchButton.click();
        return this;
    }

    public SelenideSearchBookingPage submitSearchBooking() {
        clickSearchOrderAgreeCheckbox().clickSearchButton();
        return this;
    }

    public SelenideSearchBookingPage checkErrorMessageText(String expectedText) {
        errorMessage.shouldHave(text(expectedText), Duration.ofSeconds(20));
        return this;
    }
}
