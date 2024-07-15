package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;
    private By emailInput = By.xpath("//input[@name='name']");
    private By passwordInput = By.xpath("//input[@type='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие страницы входа")
    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
    }

    @Step("Ввод электронной почты: {email}")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизация с электронной почтой: {email} и паролем")
    public void login(String email, String password) {
        this.enterEmail(email);
        this.enterPassword(password);
        this.clickLoginButton();
    }

    @Step("Проверка успешного входа в систему")
    public boolean isLoginSuccessful() {
        String expectedUrl = "https://stellarburgers.nomoreparties.site/";
        return driver.getCurrentUrl().contains(expectedUrl);
    }
}