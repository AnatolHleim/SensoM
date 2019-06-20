package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResetPasswordPage extends BasePageObject {
    ResetPasswordPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private static final String URL_AUTH = BasePageObject.BASE_URL+"auth/password-reset";

    public By inputUserEmail= By.id("email");
    public By errorNotAvailibleEmail = By.xpath("//p[@class='p error']");
    public By buttonSubmit = By.xpath("//button[@type='submit']");

    public void sendEmailToResetPassword(String text){
        type(text, inputUserEmail);
        click(buttonSubmit);
    }
}
