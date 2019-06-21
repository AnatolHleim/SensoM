package pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

public class HeaderModule extends BasePageObject {

    By logo = By.xpath("//div[@class='header__logo']");
    By headerMenu = By.xpath("//div[@class='header__menu-items']");

    public HeaderModule(WebDriver driver, Logger log) {
        super(driver, log);
    }
    private By getActivePageName(String namePage) {
        Map<String, By> messageMap = new HashMap<>();
        messageMap.put("profilePage", By.xpath(".//a[text()='Профиль']/parent::li"));
        messageMap.put("messagePage", By.xpath(".//a[text()='Оповещения']/parent::li"));
        messageMap.put("configurationPage", By.xpath(".//a[text()='Настройки']/parent::li"));
        messageMap.put("mapObjectPage", By.xpath(".//a[text()='Карта объектов']/parent::li"));
        messageMap.put("servicesPage", By.xpath(".//a[text()='Сервисное обслуживание']/parent::li"));
        return messageMap.get(namePage);
    }


    public boolean elementHasAttribute(String pageName) {
        return super.elementHasAttribute(getActivePageName(pageName), "class", "active");
    }

}
