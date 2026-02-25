package ru.stepup.testui.selenide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.stepup.testui.pages.selenide.SelenidePobedaStartPage;

public class PobedaSelenideTests {

    @Test
    @DisplayName("Открытие сайта авиакомпании 'Победа' и проверка всплывающего окна 'Информация'")
    public void testOpenPageAndCheckInformationPopup() {
        new SelenidePobedaStartPage()
                .checkPobedaStartPageIsOpened()
                .hoverOverInformationPopup()
                .checkInformationPopupHeaders();
    }

    @Test
    @DisplayName("Открытие сайта авиакомпании 'Победа' и проверка блока поиска билетов")
    public void testOpenPageAndCheckSearchTickets() {
        new SelenidePobedaStartPage()
                .checkPobedaStartPageIsOpened()
                .scrollToSearchUnit()
                .checkSearchTicketUnitInputFields()
                .setDepartureCity("Москва")
                .setArrivalCity("Санкт-Петербург")
                .clickSearchButton()
                .checkDepartureDateInputBorderColor("rgb(213, 0, 98)");
    }

    @Test
    @DisplayName("Проверка поиска несуществующего бронирования на странице 'Управление бронированием' ")
    public void testSearchNonExistingBooking() {
        new SelenidePobedaStartPage()
                .checkPobedaStartPageIsOpened()
                .openBookingManagementPage()
                .checkBookingManagementPageElements()
                .searchBooking("XXXXXX", "Qwerty")
                .checkSearchBookingPageElements()
                .submitSearchBooking()
                .checkErrorMessageText("Заказ с указанными параметрами не найден");
    }
}
