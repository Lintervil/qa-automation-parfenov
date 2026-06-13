package lesson_9;

import org.junit.jupiter.api.Test;

public class PaySectionTest extends BaseTest {

    @Test
    void checkOnlineReplenishmentSection() {
        PaySection paySection = new PaySection(driver);

        System.out.println("=== Начало проверки блока Онлайн пополнение ===");

        paySection.checkSectionTitle();
        paySection.checkPaymentLogos();
        paySection.checkDetailsLink();
        paySection.fillFormAndCheckContinueButton();

        System.out.println("Все проверки успешно пройдены!");
    }
}