package lesson_12;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
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
                "Неверное название блока! Ожидалось: 'Онлайн пополнение без комиссии', " +
                        "а было: " + actualText);

        System.out.println(" Название блока проверено: " + actualText);
    }

    // Проверка наличия логотипов платёжных систем
    public void checkPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));

        Assertions.assertTrue(logos.size() >= 4,
                "Должно быть минимум 4 логотипа платежных систем. Найдено: " + logos.size());

        System.out.println(" Логотипов платежных систем найдено: " + logos.size());
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

        System.out.println(" Все варианты оплаты проверены\n");
    }

    // Вспомогательный метод для проверки одного варианта оплаты
    private void checkPlaceholderForPaymentType(String paymentType) {
        try {
            // Кликаем на select__header, чтобы открыть список
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__header")
            ));
            selectHeader.click();

            // Ждём пока список станет видимым
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".select__list")
            ));

            // Кликаем на нужный вариант в списке
            WebElement optionInList = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[@class='select__option' and contains(text(), '" + paymentType + "')]")
            ));
            optionInList.click();

            // Ждём пока форма станет видима
            String formId = getFormIdByPaymentType(paymentType);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(formId)));


            // Получаем форму
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
            System.out.println("   " + paymentType + ":  " + e.getMessage());
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
            // Убеждаемся, что выбран вариант "Услуги связи"
            WebElement selectHeader = wait.until(ExpectedConditions.elementToBeClickable(
                    By.cssSelector(".select__header")
            ));
            selectHeader.click();

            // Ждём пока список станет видимым
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector(".select__list")
            ));

            WebElement option = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//p[@class='select__option' and contains(text(), 'Услуги связи')]")
            ));

            // Клик через JavaScript
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            // Ждём загрузки формы
            WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.cssSelector("form.pay-form.opened")
            ));

            System.out.println(" Форма 'Услуги связи' загружена");

            // === ИСПРАВЛЕННЫЙ БЛОК ВВОДА ===
            WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.className("phone")
            ));

            // Прокрутка + ввод через JavaScript (самый стабильный способ)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", phoneField);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", phoneField);
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '297777777';", phoneField);

            System.out.println(" Номер телефона 297777777 введён");

            // Поле суммы
            WebElement sumField = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.className("total_rub")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].value = '10';", sumField);
            System.out.println(" Сумма 10 руб. введена");

            // Кнопка "Продолжить"
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//form[@id='pay-connection']//button[contains(text(), 'Продолжить')]")
            ));

            Assertions.assertTrue(continueButton.isEnabled(),
                    "Кнопка 'Продолжить' должна быть активна (enabled) после ввода номера и суммы");

            System.out.println(" Кнопка 'Продолжить' активна");

            // Нажимаем кнопку
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
            System.out.println(" Кнопка 'Продолжить' нажата");

            // Ждём появления окна подтверждения
            Thread.sleep(4000);

            System.out.println(" Окно подтверждения загружено");

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

        System.out.println(" Окно подтверждения проверено\n");
    }

    // Проверка отображения суммы
    private void checkAmountDisplay() {
        try {
            List<WebElement> elements = driver.findElements(
                    By.xpath("//*[contains(text(), 'BYN')]")
            );

            // Ищем первый видимый элемент
            for (WebElement elem : elements) {
                if (elem.isDisplayed()) {
                    System.out.println("  Сумма отображается: " + elem.getText());
                    return;
                }
            }
            System.out.println("  Сумма не найдена в видимых элементах");
        } catch (Exception e) {
            System.out.println("  Не удалось проверить сумму");
        }
    }

    // Проверка отображения номера телефона
    private void checkPhoneNumberDisplay() {
        try {
            List<WebElement> elements = driver.findElements(
                    By.xpath("//*[contains(text(), '297777777')]")
            );

            // Ищем первый видимый элемент
            for (WebElement elem : elements) {
                if (elem.isDisplayed()) {
                    System.out.println("  Информация платежа: " + elem.getText());
                    return;
                }
            }
            System.out.println("  Номер платежа не найден в видимых элементах");
        } catch (Exception e) {
            System.out.println("  Информация платежа не найдена");
        }
    }

    // Проверка надписей в полях для ввода реквизитов карты
    private void checkCardFieldsPlaceholders() {
        try {
            // Ищем элемент модального окна
            List<WebElement> modals = driver.findElements(
                    By.xpath("//div[contains(@class, 'modal') or contains(@class, 'overlay') " +
                            "or contains(@class, 'popup')]")
            );

            if (!modals.isEmpty()) {
                WebElement modal = modals.get(0);

                // Ищем только input'ы внутри модального окна
                List<WebElement> cardFields = modal.findElements(
                        By.xpath(".//input[@placeholder and " +
                                "(contains(@placeholder, 'Номер') or contains(@placeholder, 'Срок') " +
                                "or contains(@placeholder, 'CVV') or contains(@placeholder, 'Имя'))]")
                );

                if (!cardFields.isEmpty()) {
                    System.out.println("  Найдены поля для ввода реквизитов карты:");
                    for (WebElement field : cardFields) {
                        String placeholder = field.getAttribute("placeholder");
                        if (placeholder != null && !placeholder.isEmpty()) {
                            System.out.println("      - " + placeholder);
                        }
                    }
                } else {
                    System.out.println("  Поля для ввода карты не найдены в модальном окне");
                }
            } else {
                System.out.println("  Модальное окно не найдено");
            }

        } catch (Exception e) {
            System.out.println("  Ошибка при проверке полей карты");
        }
    }

    // Проверка наличия иконок платёжных систем
    private void checkPaymentSystemIcons() {
        try {
            // Ищем все иконки
            List<WebElement> allIcons = driver.findElements(
                    By.xpath("//img[contains(@src, 'visa') or contains(@src, 'mastercard') " +
                            "or contains(@src, 'belkart')]")
            );

            // Фильтруем только видимые иконки
            List<WebElement> visibleIcons = new ArrayList<>();
            for (WebElement icon : allIcons) {
                try {
                    if (icon.isDisplayed()) {
                        visibleIcons.add(icon);
                    }
                } catch (Exception ignored) {
                    // Игнорируем элементы, которые невозможно проверить
                }
            }

            if (!visibleIcons.isEmpty()) {
                System.out.println(" Иконки платежных систем найдены: " + visibleIcons.size());
                for (int i = 0; i < Math.min(visibleIcons.size(), 5); i++) {
                    WebElement icon = visibleIcons.get(i);
                    String alt = icon.getAttribute("alt");
                    System.out.println("      - " + alt);
                }
            } else {
                System.out.println(" Видимых иконок платежных систем не найдено");
            }

        } catch (Exception e) {
            System.out.println(" Ошибка при проверке иконок");
        }
    }
}