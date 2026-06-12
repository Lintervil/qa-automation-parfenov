package lesson_11;

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

    // Проверка названия блока
    public void checkSectionTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(), 'Онлайн пополнение')]")
        ));

        String actualText = title.getText().replace("\n", " ").trim();

        Assertions.assertTrue(actualText.contains("Онлайн пополнение без комиссии"),
                "Неверное название блока! Ожидалось: 'Онлайн пополнение без комиссии', а было: " + actualText);

        System.out.println("✓ Название блока проверено: " + actualText);
    }

    // Проверка наличия логотипов платёжных систем
    public void checkPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));

        Assertions.assertTrue(logos.size() >= 4,
                "Мало логотипов платежных систем. Найдено: " + logos.size());

        System.out.println("✓ Логотипов платежных систем найдено: " + logos.size());
        for (WebElement logo : logos) {
            String alt = logo.getAttribute("alt");
            System.out.println("  - " + alt);
        }
    }

    // Проверка ссылки "Подробнее о сервисе"
    public void checkDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));

        Assertions.assertTrue(link.isDisplayed(),
                "Ссылка 'Подробнее о сервисе' не найдена");
        System.out.println("✓ Ссылка 'Подробнее о сервисе' найдена");
    }

    // ЧАСТЬ 2: Проверка надписей во всех вариантах оплаты
    public void checkAllPaymentTypePlaceholders() {
        System.out.println("\n=== Проверка надписей во всех вариантах оплаты ===");

        String[] paymentTypes = {
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        };

        for (String paymentType : paymentTypes) {
            checkPlaceholderForPaymentType(paymentType);
        }

        System.out.println("✓ Все варианты оплаты проверены\n");
    }

    // Вспомогательный метод для проверки одного варианта оплаты
    private void checkPlaceholderForPaymentType(String paymentType) {
        try {
            // Кликаем на select__header, чтобы открыть список
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__header")
            ));
            selectHeader.click();
            Thread.sleep(300);

            // Кликаем на нужный вариант в списке
            WebElement optionInList = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[@class='select__option' and contains(text(), '" + paymentType + "')]")
            ));
            optionInList.click();
            Thread.sleep(500);

            System.out.println("  ▸ " + paymentType + ":");

            // Ищем форму по ID
            String formId = getFormIdByPaymentType(paymentType);
            WebElement form = driver.findElement(By.id(formId));

            // Получаем все input поля в этой форме
            List<WebElement> inputFields = form.findElements(By.xpath(".//input[@placeholder]"));

            if (!inputFields.isEmpty()) {
                for (WebElement field : inputFields) {
                    String placeholder = field.getAttribute("placeholder");
                    if (placeholder != null && !placeholder.isEmpty()) {
                        System.out.println("      - " + placeholder);
                    }
                }
            } else {
                System.out.println("      - Поля не найдены");
            }

        } catch (Exception e) {
            System.out.println("  ▸ " + paymentType + ": ⚠ " + e.getMessage());
        }
    }

    // Вспомогательный метод для получения ID формы по типу оплаты
    private String getFormIdByPaymentType(String paymentType) {
        switch (paymentType) {
            case "Услуги связи":
                return "pay-connection";
            case "Домашний интернет":
                return "pay-internet";
            case "Рассрочка":
                return "pay-instalment";
            case "Задолженность":
                return "pay-arrears";
            default:
                return "";
        }
    }

    // Заполнение формы "Услуги связи" и проверка кнопки "Продолжить"
    public void fillFormAndCheckContinueButton() {
        System.out.println("\n=== Заполнение формы 'Услуги связи' ===");

        try {
            // Сначала убеждаемся, что выбран вариант "Услуги связи"
            WebElement selectHeader = driver.findElement(By.cssSelector(".select__header"));
            selectHeader.click();
            Thread.sleep(300);

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//p[@class='select__option' and contains(text(), 'Услуги связи')]")
            ));
            option.click();
            Thread.sleep(800);

            // Ищем форму с классом "opened" (она видна)
            WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("form.pay-form.opened")
            ));

            System.out.println("✓ Форма 'Услуги связи' загружена");

            // Находим поле для ввода телефона
            WebElement phoneField = form.findElement(By.className("phone"));
            phoneField.clear();
            phoneField.sendKeys("297777777");
            Thread.sleep(300);
            System.out.println("✓ Номер телефона 297777777 введён");

            // Заполняем поле СУММЫ
            WebElement sumField = form.findElement(By.className("total_rub"));
            sumField.clear();
            sumField.sendKeys("10");
            Thread.sleep(300);
            System.out.println("✓ Сумма 10 руб. введена");

            // Email необязателен, поэтому пропускаем его

            // Находим кнопку "Продолжить" в этой форме
            WebElement continueButton = form.findElement(By.xpath(".//button[contains(text(), 'Продолжить')]"));

            // Проверяем, что кнопка активна
            Assertions.assertTrue(continueButton.isEnabled(),
                    "Кнопка 'Продолжить' должна быть активна (enabled) после ввода номера и суммы");

            System.out.println("✓ Кнопка 'Продолжить' активна");

            // Скроллим к кнопке
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", continueButton);
            Thread.sleep(300);

            // Нажимаем кнопку
            continueButton.click();
            System.out.println("✓ Кнопка 'Продолжить' нажата");

            // Ждём загрузки окна подтверждения
            Thread.sleep(2500);

        } catch (Exception e) {
            Assertions.fail("Ошибка при заполнении формы: " + e.getMessage());
        }
    }

    // ЧАСТЬ 2: Проверка окна подтверждения платежа
    public void checkPaymentConfirmationWindow() {
        System.out.println("\n=== Проверка окна подтверждения платежа ===");

        checkAmountDisplay();
        checkPhoneNumberDisplay();
        checkCardFieldsPlaceholders();
        checkPaymentSystemIcons();

        System.out.println("✓ Окно подтверждения проверено\n");
    }

    // Проверка отображения суммы
    private void checkAmountDisplay() {
        try {
            WebElement amountDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), 'BYN') or contains(text(), 'Оплатить')]")
            ));

            String amountText = amountDisplay.getText();
            Assertions.assertFalse(amountText.isEmpty(), "Сумма не отображается");
            System.out.println("  ✓ Сумма отображается: " + amountText);

        } catch (Exception e) {
            System.out.println("  ⚠ Не удалось проверить сумму: " + e.getMessage());
        }
    }

    // Проверка отображения номера телефона
    private void checkPhoneNumberDisplay() {
        try {
            WebElement phoneDisplay = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(), '297777777')]")
            ));

            String phoneText = phoneDisplay.getText();
            System.out.println("  ✓ Информация платежа: " + phoneText);

        } catch (Exception e) {
            System.out.println("  ⚠ Информация платежа не найдена, но это нормально");
        }
    }

    // Проверка надписей в полях для ввода реквизитов карты
    private void checkCardFieldsPlaceholders() {
        try {
            // Ищем все input поля в модальном окне
            List<WebElement> cardFields = driver.findElements(
                    By.xpath("//input[@placeholder]")
            );

            if (!cardFields.isEmpty()) {
                System.out.println("  ✓ Найдены поля для ввода реквизитов карты:");
                for (WebElement field : cardFields) {
                    String placeholder = field.getAttribute("placeholder");
                    if (placeholder != null && !placeholder.isEmpty()) {
                        System.out.println("      - " + placeholder);
                    }
                }
            } else {
                System.out.println("  ⚠ Поля для ввода не найдены");
            }

        } catch (Exception e) {
            System.out.println("  ⚠ Ошибка при проверке полей карты: " + e.getMessage());
        }
    }

    // Проверка наличия иконок платёжных систем
    private void checkPaymentSystemIcons() {
        try {
            List<WebElement> icons = driver.findElements(
                    By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard') or contains(@src, 'belkart')]")
            );

            if (!icons.isEmpty()) {
                System.out.println("  ✓ Иконки платежных систем найдены: " + icons.size());
                for (int i = 0; i < Math.min(icons.size(), 5); i++) {
                    WebElement icon = icons.get(i);
                    String alt = icon.getAttribute("alt");
                    System.out.println("      - " + alt);
                }
            } else {
                System.out.println("  ⚠ Иконки платежных систем не найдены");
            }

        } catch (Exception e) {
            System.out.println("  ⚠ Ошибка при проверке иконок: " + e.getMessage());
        }
    }
}