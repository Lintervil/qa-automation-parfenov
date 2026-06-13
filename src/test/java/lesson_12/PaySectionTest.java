package lesson_12;   // ← измени на свой пакет, если нужно (lesson_9 или lesson_11)

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;

@Epic("Selenium Web UI Testing")
@Feature("MTS.by - Блок Онлайн пополнение")
@Story("Полная проверка функционала пополнения")
@Owner("Parfenov")
public class PaySectionTest extends BaseTest {

    private PaySection paySection;

    @BeforeEach
    void setUpTest() {
        paySection = new PaySection(driver);
    }

    @Test
    @DisplayName("Полная проверка блока 'Онлайн пополнение без комиссии'")
    @Description("Проверка названия, логотипов, плейсхолдеров, заполнения формы и модального окна")
    @Severity(SeverityLevel.CRITICAL)
    void checkOnlineReplenishmentFull() {

        Allure.step("Открытие главной страницы MTS.by", () -> {});

        Allure.step("1. Проверка названия блока", paySection::checkSectionTitle);
        Allure.step("2. Проверка логотипов платежных систем", paySection::checkPaymentLogos);
        Allure.step("3. Проверка ссылки 'Подробнее о сервисе'", paySection::checkDetailsLink);
        Allure.step("4. Проверка плейсхолдеров во всех вариантах оплаты", paySection::checkAllPaymentTypePlaceholders);

        Allure.step("5. Заполнение формы 'Услуги связи' и проверка кнопки 'Продолжить'",
                paySection::fillFormAndCheckContinueButton);

        Allure.step("6. Проверка окна подтверждения платежа",
                paySection::checkPaymentConfirmationWindow);

        // Правильное добавление скриншота в Allure
        Allure.addAttachment("Финальный скриншот",
                "image/png",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)),
                "png");
    }
}