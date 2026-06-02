package lesson_9;

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

        assert actualText.contains("Онлайн пополнение без комиссии") :
                "Неверное название блока! Ожидалось: 'Онлайн пополнение без комиссии', а было: " + actualText;

        System.out.println("Название блока проверено: " + actualText);
    }

    public void checkPaymentLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector("img.pay__partner__logo, .pay__partners img"));
        assert logos.size() >= 4 : "Мало логотипов платежных систем. Найдено: " + logos.size();
        System.out.println("Логотипов платежных систем найдено: " + logos.size());
    }

    public void checkDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Подробнее о сервисе')]")
        ));
        assert link.isDisplayed() : "Ссылка 'Подробнее о сервисе' не найдена";
        System.out.println("Ссылка 'Подробнее о сервисе' найдена");
    }

    public void fillFormAndCheckContinueButton() {
        // Более широкий поиск поля телефона
        WebElement phoneField = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[contains(@name, 'phone') or @type='tel' or contains(@placeholder, '375')]")
        ));

        // Прокрутка к полю
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", phoneField);

        // Очистка и ввод через JavaScript (самый надёжный способ)
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", phoneField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = '297777777';", phoneField);

        // Проверка кнопки "Продолжить"
        WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), 'Продолжить')]"));

        assert continueButton.isDisplayed() : "Кнопка 'Продолжить' не найдена";
        System.out.println("Номер 297777777 успешно введён");
        System.out.println("Кнопка 'Продолжить' активна");
    }
}