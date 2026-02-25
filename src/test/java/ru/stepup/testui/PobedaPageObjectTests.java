package ru.stepup.testui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stepup.testui.pages.BookingManagmentPage;
import ru.stepup.testui.pages.SearchOrderPage;
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

    @Test
    @DisplayName("Открытие сайта авиакомпании 'Победа' и проверка блока поиска билетов")
    public void testOpenPageAndCheckSearchTickets() {
        PobedaStartPage pobedaStartPage = new PobedaStartPage(driver);

        assertThat("Заголовок не совпадает", pobedaStartPage.getTitleText(),
                equalTo("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
                        "прямые и трансферные рейсы с пересадками"));
        assertThat("На странице не отображается логотип Победы",
                pobedaStartPage.isLogoDisplayed(), equalTo(true));

        pobedaStartPage.scrollToSearchUnit();
        assertThat("Не отображается поле выбора города отправления",
                pobedaStartPage.isDepartureCityInputVisible(), equalTo(true));
        assertThat("Не отображается поле выбора города назначения",
                pobedaStartPage.isArrivalCityInputVisible(), equalTo(true));
        assertThat("Не отображается поле выбора даты отправления",
                pobedaStartPage.isDepartureDateInputVisible(), equalTo(true));
        assertThat("Не отображается поле выбора даты обратного рейса",
                pobedaStartPage.isReturningDateInputVisible(), equalTo(true));

        pobedaStartPage.setDepartureCity("Москва");
        pobedaStartPage.setArrivalCity("Санкт-Петербург");
        pobedaStartPage.submitSearchButton();
        assertThat("Поле 'Туда' не подсвечено красной обводкой",
                pobedaStartPage.getDepartureDateInputBorderColor(), equalTo("rgb(213, 0, 98)"));
    }

    @Test
    @DisplayName("Проверка поиска несуществующего бронирования на странице 'Управление бронированием' ")
    public void testSearchNonExistingBooking() {
        PobedaStartPage pobedaStartPage = new PobedaStartPage(driver);
        BookingManagmentPage bookingManagmentPage = new BookingManagmentPage(driver);

        assertThat("Заголовок не совпадает", pobedaStartPage.getTitleText(),
                equalTo("Авиакомпания «Победа» - купить авиабилеты онлайн, дешёвые билеты на самолёт, " +
                        "прямые и трансферные рейсы с пересадками"));
        assertThat("На странице не отображается логотип Победы",
                pobedaStartPage.isLogoDisplayed(), equalTo(true));

        // Переходим на первую страницу "Управление бронирования" - не учтено в задании
        pobedaStartPage.openManageBookingFirstPage();
        assertThat("На первой странице 'Управление бронированием' не отображается поле 'Номер бронирования или билета'",
                bookingManagmentPage.isOrderNumberInputVisible(), equalTo(true));
        assertThat("На первой странице 'Управление бронированием' не отображается поле 'Фамилия клиента'",
                bookingManagmentPage.isClientLastNameInputVisible(), equalTo(true));
        assertThat("На первой странице 'Управление бронированием' не отображается кнопка 'Поиск'",
                bookingManagmentPage.isSearchButtonVisible(), equalTo(true));

        // После нажатия на кнопку "Поиск" переходим на страницу поиска брони
        SearchOrderPage searchOrderPage = bookingManagmentPage
                .searchBooking("XXXXXX", "Qwerty");
        assertThat("На второй странице 'Управление бронированием' не отображается поле 'Номер брони или билета'",
                searchOrderPage.isOrderNumberInputVisible(), equalTo(true));
        assertThat("На второй странице 'Управление бронированием' не отображается поле 'Фамилия'",
                searchOrderPage.isClientLastNameInputVisible(), equalTo(true));
        assertThat("На второй странице 'Управление бронированием' не отображается кнопка 'Найти заказ'",
                searchOrderPage.isSearchButtonVisible(), equalTo(true));

        searchOrderPage.submitSearchBooking();
        assertThat("На странице 'Управление бронированием' не отображается сообщение об ошибке поиска",
                searchOrderPage.isErrorMessageVisible(), equalTo(true));
        assertThat("Текст сообщения об ошибке поиска некорректен",
                searchOrderPage.getErrorMessageText(),
                equalTo("Заказ с указанными параметрами не найден"));
    }

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
    }

    @AfterEach
    public void quitDriver() {
        driver.quit();
    }
}
