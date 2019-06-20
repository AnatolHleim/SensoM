package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.util.HashMap;

import java.util.Map;

public class NewObjectPage extends BasePageObject {
    public NewObjectPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private static final String URL_NEW_OBJECT = BasePageObject.BASE_URL;

    public NewObjectPage openPage() {
        openUrl(URL_NEW_OBJECT);
        return new NewObjectPage(driver, log);
    }

    public By selectMessage(String nameMessage) {
        Map<String, By> messageMap = new HashMap<String, By>();
        messageMap.put("errorNameObjectInput", By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Название')]]//div/p[@class='error']"));
        messageMap.put("errorAddressObjectInput", By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Адрес')]]//div/p[@class='error']"));
        messageMap.put("errorLoginInput", By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Логин')]]//div/p[@class='error']"));
        messageMap.put("errorPasswordInput", By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Пароль')]]//div/input"));
        messageMap.put("successSaveOwnerBlockMessage", By.xpath("//div[@class='objects new-object']//p[@class='submit-form-info']"));
        return messageMap.get(nameMessage);
    }

    public By ownerField = By.xpath("//div[@class='input datalist' and preceding-sibling::div/h2[text()='Владелец процесса']]");
    public By firstEngeneerField = By.xpath("//div[@class='input datalist' and preceding-sibling::div/h2[text()='Инженер 1']]");
    public By managerField = By.xpath("//div[@class='input datalist' and preceding-sibling::div/h2[text()='Менеджер 1']]");
    public By nameObjectInput = By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Название')]]//div/input");
    public By addressObjectInput = By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Адрес')]]//div/input");
    public By urlObjectInput = By.xpath("//div[div[@class='input-title']/h2[contains(text(),'URL')]]//div/input");
    public By checkBoxAuth = By.xpath("//div[@class='label useAuthentication']/label/span");
    public By loginInput = By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Логин')]]//div/input");
    public By passwordInput = By.xpath("//div[div[@class='input-title']/h2[contains(text(),'Пароль')]]//div/input");
    public By buttonLoadLogo = By.xpath("//label[@class='button ng-untouched ng-pristine ng-valid']");
    public By inputPathLogo = By.xpath("//label[@class='button ng-untouched ng-pristine ng-valid']/input");
    public By buttonLoadDefaultLogo = By.xpath("//button[text()='Выбрать из списка']");
    public By errorForLogoLoad = By.xpath("//p[contains(text(),'Файл должен быть в формате JPG')]");
    public By snowCoverInput = By.xpath("//div[@class='label snowCover']/input");
    public By buttonSaveOwnerBlock = By.xpath("//div[@class='objects new-object']//button[text()='Сохранить']");
    public By svgLogoList = By.xpath("//div[@class='chooseFromList-wrap']");


}
