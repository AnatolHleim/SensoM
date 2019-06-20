package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class AuthPage extends BasePageObject {
    public static final String URL_AUTH = BasePageObject.BASE_URL + "auth/login";

    public AuthPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public AuthPage openPage() {
        // log.info("Opening page: " + URL_AUTH);
        openUrl(URL_AUTH);
        //log.info("Page opened!");
        return new AuthPage(driver, log);
    }

    public MainPage logIn(String userName, String password) {
        type(userName, inputUserEmail);
        type(password, inputPassword);
        click(buttonSubmit);
        return new MainPage(driver, log);
    }

    public ResetPasswordPage goToResetPasswordPage() {
        click(linkForgotPassword);
        return new ResetPasswordPage(driver, log);
    }

    public boolean elementHasAttribute(By element, String attribute, String active) {
        return this.find(element).getAttribute(attribute).contains(active);
    }

    public By inputUserEmail = By.id("email");
    public By inputPassword = By.id("password");
    public By buttonSubmit = By.xpath("//button[@ng-reflect-klass='button']");
    public By linkForgotPassword = By.cssSelector("div.auth__forgot-password > a");
    public By buttonShowPassword = By.xpath("//div[@class='auth__password-eye']");
    public By modalWindowRequisites = By.xpath("//div[@ng-reflect-klass='requisites']");
    public By linkFooterRequisites = By.xpath("//span[@class='link footer__left-item p1']");
    public By buttonCloseModalRequisites = By.xpath("//div[@class='requisites__close']");

}