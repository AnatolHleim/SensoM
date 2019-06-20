package newObjectPage;

import base.TestData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.NewObjectPage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class NewObjectPageTest extends TestData {
    @Test
    public void verifyTitleNewObjectPage(){
        NewObjectPage newObjectPage = new NewObjectPage(driver,log).openPage();
        Assert.assertEquals(newObjectPage.getCurrentPageTitle(),"Client");
    }

    @Test(dataProvider = "getDataNewObject" )
    public void inputDataFirstBlock(TestData data){
        NewObjectPage newObjectPage = new NewObjectPage(driver,log).openPage();
        newObjectPage.type(data.getOwnerName(),newObjectPage.ownerField);
        newObjectPage.type(data.getEngineerName(),newObjectPage.firstEngeneerField);
        newObjectPage.type(data.getManagerName(),newObjectPage.managerField);
        newObjectPage.type(data.getObjectName(),newObjectPage.nameObjectInput);
        newObjectPage.type(data.getObjectAddress(),newObjectPage.addressObjectInput);
        newObjectPage.type(data.getObjectURL(),newObjectPage.urlObjectInput);
        newObjectPage.type(data.getUsername(),newObjectPage.loginInput);
        newObjectPage.type(data.getPassword(),newObjectPage.passwordInput);
        newObjectPage.click(newObjectPage.checkBoxAuth);
        newObjectPage.click(newObjectPage.buttonLoadDefaultLogo);
        newObjectPage.click(newObjectPage.svgLogoList);
        newObjectPage.type(data.getSnowCover(),newObjectPage.snowCoverInput);
        newObjectPage.click(newObjectPage.buttonSaveOwnerBlock);
        Assert.assertEquals(newObjectPage.find(newObjectPage.selectMessage(data.getLocator())).getText(),data.getMessageText());

    }
    @DataProvider
    public Object[][] getDataNewObject() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/newObjectData.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("dataSet");
        List<TestData> testData = new Gson().fromJson(dataSet, new TypeToken<List<TestData>>() {
        }.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }
}
