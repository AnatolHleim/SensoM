package pages;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePageObject {
    public static final String URL_MAIN = "http://31.130.206.73:3210/app";
    MainPage(WebDriver driver, Logger log) {
        super(driver,log);
    }
@FindBy (xpath = "//div[@class='card card-add-obj']")
    By addNewObject;
    public By welcome = By.xpath("//div[@class='notFound__text']");
    public NewObjectPage addNewObject (){
        //click(addNewObject);
        return new NewObjectPage(driver,log);
    }
}
