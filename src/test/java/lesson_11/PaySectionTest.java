package lesson_11;

import org.junit.jupiter.api.Test;

public class PaySectionTest extends BaseTest {

    @Test
    void checkOnlineReplenishmentSectionPart2() {
        PaySection paySection = new PaySection(driver);

        System.out.println("=== Начало проверки блока 'Онлайн пополнение' (Часть 2) ===\n");

        // Часть 1: базовые проверки
        paySection.checkSectionTitle();
        paySection.checkPaymentLogos();
        paySection.checkDetailsLink();

        // Часть 2: проверка надписей во всех вариантах оплаты
        paySection.checkAllPaymentTypePlaceholders();

        // Часть 2: заполнение формы "Услуги связи" и проверка окна подтверждения
        paySection.fillFormAndCheckContinueButton();
        paySection.checkPaymentConfirmationWindow();

        System.out.println("--- Все проверки успешно пройдены! ---\n");
    }
}