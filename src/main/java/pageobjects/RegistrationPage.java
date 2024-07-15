package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private By passwordError = By.xpath("//*[text()='Некорректный пароль']");
    private String registrationUrl = "https://stellarburgers.nomoreparties.site/register";

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы регистрации")
    public void open() {
        driver.get(registrationUrl);
    }

    @Step("Получение поля ввода имени")
    public WebElement getNameInput() {
        return driver.findElement(By.xpath("//input[@name='name']"));
    }

    @Step("Получение поля ввода электронной почты")
    public WebElement getEmailInput() {
        return driver.findElements(By.cssSelector("input.input__textfield")).get(1);
    }

    @Step("Получение поля ввода пароля")
    public WebElement getPasswordInput() {
        return driver.findElement(By.xpath("//input[@type='password']"));
    }

    @Step("Получение кнопки регистрации")
    public WebElement getRegisterButton() {
        return driver.findElement(By.cssSelector("button.button_button_type_primary__1O7Bx"));
    }

    @Step("Регистрация пользователя с именем, почтой и паролем")
    public void register(String name, String email, String password) {
        this.getNameInput().clear();
        this.getNameInput().sendKeys(name);
        this.getEmailInput().clear();
        this.getEmailInput().sendKeys(email);
        this.getPasswordInput().clear();
        this.getPasswordInput().sendKeys(password);
        this.getRegisterButton().click();
    }

    @Step("Проверка успешной регистрации")
    public boolean isSuccessfulRegistration() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Проверка отображения ошибки ввода пароля")
    public boolean isPasswordErrorDisplayed() {
        try {
            return driver.findElement(passwordError).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Переход на страницу входа")
    public void navigateToLogin() {
        By loginLink = By.xpath("//a[contains(text(),'Войти') or contains(@href, '/login')]");
        driver.findElement(loginLink).click();
    }
}