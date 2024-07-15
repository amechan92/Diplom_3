package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.*;
import utils.WebDriverFactory;

public class ProfileTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;

    @Before
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.createDriver(browserName);

        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void goToProfileFromMainPage() {
        loginPage.open();
        loginPage.login("parashepa@gmail.com", "123456");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(mainPage.getProfileLink()));
        mainPage.goToProfile();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/account/profile"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL не соответствует ожидаемому", "https://stellarburgers.nomoreparties.site/account/profile", currentUrl);
    }

    @Test
    public void goToMainPageByLogoFromProfile() {
        loginPage.open();
        loginPage.login("parashepa@gmail.com", "123456");
        mainPage.goToProfile();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.getLogo()));
        profilePage.clickLogo();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL не соответствует ожидаемому", "https://stellarburgers.nomoreparties.site/", currentUrl);
    }

    @Test
    public void goToMainPageByButtonFromProfile() {
        loginPage.open();
        loginPage.login("parashepa@gmail.com", "123456");
        mainPage.goToProfile();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.getConstructorButton()));
        profilePage.goToMainByConstructorButton();
        wait.until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("URL не соответствует ожидаемому", "https://stellarburgers.nomoreparties.site/", currentUrl);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}