package ru.stepup.testui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stepup.testui.pages.PobedaStartPage;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PobedaPageObjectTests {
    private final static String BASE_URL = "https://www.flypobeda.ru/";

    WebDriver driver;

    @Test
    @DisplayName("Открытие сайта авиакомпании 'Победа' и проверка всплывающего окна 'Информация'")
    public void testOpenPageAndCheckInformationPopup() {
        PobedaStartPage pobedaStartPage = new PobedaStartPage(driver);

        assertThat("Заголовок не совпадает", pobedaStartPage.getTitleText(),
                equalTo("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
                        "прямые и трансферные рейсы с пересадками"));
        assertThat("На странице не отображается логотип Победы",
                pobedaStartPage.isLogoDisplayed(), equalTo(true));

        pobedaStartPage.hoverOverInformationPopup();
        assertThat("Заголовок 'Подготовка к полету' не отображается",
                pobedaStartPage.isPrepareForFlightHeaderVisible(), equalTo(true));
        assertThat("Заголовок 'Полезная информация' не отображается",
                pobedaStartPage.isUsefulInfoHeaderVisible(), equalTo(true));
        assertThat("Заголовок 'О компании' не отображается",
                pobedaStartPage.isAboutCompanyHeaderVisible(), equalTo(true));
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
