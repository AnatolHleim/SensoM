package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordPage extends BasePageObject {
    ResetPasswordPage(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private static final String URL_AUTH = BasePageObject.BASE_URL+"auth/password-reset";

    private By inputUserEmail= By.id("email");
    private By buttonSubmit = By.xpath("//button[@type='submit']");
    public boolean selectMessage(String nameMessage,String condition,@Optional String attribute,@Optional String active ) {
        Map<String, By> messageMap = new HashMap<String, By>();
        messageMap.put("errorNotAvailableEmail", By.xpath("//p[@class='p error']"));
        messageMap.put("disableButton", By.xpath("//button[@ng-reflect-klass='button']"));
        messageMap.put("successSendMessage", By.xpath("//div[@class='reset__submit-msg']"));
        if(condition.equals("containAttribute")){
          return   elementHasAttribute(messageMap.get(nameMessage),attribute,active);
        }else if (condition.equals("displayed")){
          return find(messageMap.get(nameMessage)) .isDisplayed();
        }else return false;
    }
    public void sendEmailToResetPassword(String text){
        type(text, inputUserEmail);
        click(buttonSubmit);
    }
}
