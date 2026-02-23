package ru.stepup.testui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PikabuTest {
    private final static String BASE_URL = "https://pikabu.ru/";
    WebDriver driver;

    @BeforeEach
    public void openPageByChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\projects\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    private void clickOnLoginButton() {
        driver.findElement(By.cssSelector("button[class*=\"login\"]")).click();
    }

    private void checkAuthModalWindowIsOpened() {
        assertThat("Модальное окно «Авторизация» не отображается",
                driver.findElement(By.cssSelector("div[class=\"auth-modal\"]")).isDisplayed(), equalTo(true));
        assertThat("Поле ввода «Логин» не отображается",
                driver.findElement(By.cssSelector("div[class=\"auth-modal\"] input[placeholder=\"Логин\"]"))
                        .isDisplayed(), equalTo(true));
        assertThat("Поле ввода «Пароль» не отображается",
                driver.findElement(By.cssSelector("div[class=\"auth-modal\"] input[placeholder=\"Пароль\"]"))
                        .isDisplayed(), equalTo(true));
    }

    private void logInByUsernameAndPass() {
        driver.findElement(By.cssSelector("div[class=\"auth-modal\"] input[placeholder=\"Логин\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("div[class=\"auth-modal\"] input[placeholder=\"Пароль\"]")).sendKeys("Qwerty");
        driver.findElement(By.cssSelector("div[class=\"auth-modal\"] button[type=\"submit\"] > span")).click();
    }

    private void checkAuthErrorIsDisplayed() {
        WebElement authErrorMessage = driver.findElement(By.cssSelector("div[class=\"auth__notice\"]~span[class*=\"auth__error\"]"));
        assertThat("Сообщение об ошибке авторизации не отображается",
                authErrorMessage.isDisplayed(), equalTo(true));
        assertThat("Текст ошибки не совпадает с ожидаемым текстом ошибки авторизации",
                authErrorMessage.getText(), equalTo("Ошибка. Вы ввели неверные данные авторизации"));
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }

    @Test
    public void testPikabuAuthorizationError() {
        assertThat("Неверный заголовок сайта", driver.getTitle(),
                equalTo("Горячее – самые интересные и обсуждаемые посты | Пикабу"));
        clickOnLoginButton();
        checkAuthModalWindowIsOpened();
        logInByUsernameAndPass();
        checkAuthErrorIsDisplayed();
    }
}
