package ru.stepup.testui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.awaitility.Awaitility.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class PobedaTests {
    private final static String DUCKGO_URL = "https://duckduckgo.com/";
    private final static String EXAMPLE_TEXT = "Полетели в Калининград!";

    WebDriver driver;

    @Test
    public void testSearchAndOpenPobedaPage() {
        searchForPobedaSite();
        openPobedaSite();
        customWaitForElementsKaliningrad();
        changeLanguage();
        checkElementsAfterChangeLanguage();
    }

    @BeforeEach
    public void openGooglePage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(DUCKGO_URL);
    }

    private void searchForPobedaSite() {
        WebElement searchInput = driver.findElement(By.cssSelector("input[name='q']"));
        searchInput.sendKeys("Сайт компании Победа");
        searchInput.sendKeys(Keys.ENTER);
    }

    private void openPobedaSite() {
        WebElement pobedaLink = driver.findElement(By.cssSelector("a[href='https://www.flypobeda.ru/']"));
        pobedaLink.click();
        assertThat(driver.getTitle(), containsString("Авиакомпания «Победа»"));
    }

    private static void waitForElementIsVisible(WebElement element) {
        with()
                .pollDelay(Duration.ofSeconds(1))
                .await()
                .atMost(Duration.ofSeconds(100))
                .until(element::isDisplayed);
    }

    private void customWaitForElementsKaliningrad() {
        WebElement kaliningradText = driver.
                findElement(By.xpath("//div[contains(text(), '" + EXAMPLE_TEXT + "')]"));
        WebElement kaliningradPicture = driver.findElement(By.cssSelector("img[srcset*=KALINIGRAD]"));
        waitForElementIsVisible(kaliningradPicture);
        waitForElementIsVisible(kaliningradText);
        assertThat(kaliningradText.getText(), equalTo("Полетели в Калининград!"));
    }

    private void explicitWaitTextOfElement(WebDriverWait wait, By locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    private void changeLanguage() {
        driver.findElement(By.cssSelector("button[aria-label='Меню']")).click();
        driver.findElement(By.xpath("//div[@tabindex]//button[contains(text(), 'РУС')]")).click();
        driver.findElement(By.xpath("//div[contains(text(), 'English')]")).click();
    }

    private void checkElementsAfterChangeLanguage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        explicitWaitTextOfElement(wait, By.xpath("//span[contains(text(), 'Ticket search')][2]"),
                "Ticket search");
        explicitWaitTextOfElement(wait, By.xpath("//span[contains(text(), 'Online check-in')][2]"), "Online check-in");
        explicitWaitTextOfElement(wait, By.xpath("//span[contains(text(), 'Manage my booking')][2]"), "Manage my booking");
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
