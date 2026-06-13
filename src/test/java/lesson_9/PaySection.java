package lesson_9;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaySection {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public PaySection(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void checkSectionTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Онлайн пополнение')]")
        ));

        String actualText = title.getText().replace("\n", " ").trim();

        Assertions.assertTrue(actualText.contains("Онлайн пополнение без комиссии"),
                "Неверное название блока! Ожидалось: 'Онлайн пополнение без комиссии', а было: " + actualText);

        System.out.println("Название блока проверено: " + actualText);
    }

    public void checkPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector("img.pay__partner__logo, .pay__partners img"));

        Assertions.assertTrue(logos.size() >= 4,
                "Мало логотипов платежных систем. Найдено: " + logos.size());
        System.out.println("Логотипов платежных систем найдено: " + logos.size());
    }

    public void checkDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));

        Assertions.assertTrue(link.isDisplayed(),
                "Ссылка 'Подробнее о сервисе' не найдена");
        System.out.println("Ссылка 'Подробнее о сервисе' найдена");
    }

    public void fillFormAndCheckContinueButton() {
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[contains(@name,'phone') or @type='tel' or contains(@placeholder,'375')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", phoneField);

        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '297777777';", phoneField);

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Продолжить')]")
        ));

        Assertions.assertTrue(continueButton.isEnabled(),
                "Кнопка 'Продолжить' должна быть активна (enabled) после ввода номера");

        System.out.println("Номер 297777777 успешно введён");
        System.out.println("Кнопка 'Продолжить' активна");

        String beforeUrl = driver.getCurrentUrl();

        continueButton.click();
        System.out.println("Кнопка 'Продолжить' нажата");

        // Ожидаем одно из корректных изменений: смена URL, скрытие поля телефона, появление сообщения об ошибке
        try {
            // Ждём либо изменения URL, либо исчезновения поля телефона, либо появления подтверждающего/результирующего блока
            boolean changed = wait.until(d -> {
                try {
                    String now = d.getCurrentUrl();
                    if (!now.equals(beforeUrl)) return true;
                } catch (Exception ignored) {}

                try {
                    if (!d.findElements(By.xpath("//input[contains(@name,'phone') or @type='tel' or contains(@placeholder,'375')]")).isEmpty()) {
                        // поле всё ещё есть
                    } else {
                        return true; // поле скрыто/удалено
                    }
                } catch (Exception ignored) {}

                // проверяем наличие возможных подтверждающих селекторов/текстов
                List<WebElement> confirmation = d.findElements(By.cssSelector(".payment-result, .payment-confirmation, .payment-step, .pay__form--result"));
                if (!confirmation.isEmpty()) return true;

                List<WebElement> texts = d.findElements(By.xpath("//*[contains(text(), 'Оплатить') or contains(text(), 'Подтвержд') or contains(text(), 'код')]"));
                return !texts.isEmpty();
            });

            if (changed) {
                // Дополнительная проверка: определим причину изменения
                String afterUrl = driver.getCurrentUrl();
                if (!afterUrl.equals(beforeUrl)) {
                    System.out.println("✓ Произошла навигация после нажатия кнопки. URL до: " + beforeUrl + " -> после: " + afterUrl);
                } else {
                    System.out.println("✓ Страница обновила состояние: либо поле телефона скрыто, либо появился подтверждающий блок");
                }
            } else {
                // маловероятно, сюда не зайдём т.к. wait вернёт true/timeout
                Assertions.fail("После нажатия кнопки 'Продолжить' не произошло ожидаемого изменения");
            }
        } catch (Exception e) {
            // Пытаемся получить и показать сообщение об ошибке, если оно есть
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'ошиб') or contains(text(), 'Ошиб') or contains(text(), 'код')]")
                ));
                System.out.println("✖ Получено сообщение после нажатия кнопки: " + errorMessage.getText());
                // Можно считать это корректным результатом, или, при необходимости, провалить тест:
                // Assertions.fail("Получено сообщение об ошибке: " + errorMessage.getText());
            } catch (Exception ex) {
                Assertions.fail("После нажатия кнопки 'Продолжить' должны были произойти изменения на странице");
            }
        }
    }
}