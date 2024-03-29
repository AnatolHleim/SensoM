package newObjectPage;

import base.TestData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HeaderModule;
import pages.NewObjectPage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class NewObjectPageTest extends TestData {
    @Test
    public void verifyTitleNewObjectPageAndHeaderVisible() {
        NewObjectPage newObjectPage = new NewObjectPage(driver, log).openPage();
        HeaderModule headerModule = new HeaderModule(driver, log);
        Assert.assertEquals(newObjectPage.getCurrentPageTitle(), "Client");
        Assert.assertTrue(headerModule.elementHasAttribute("profilePage"));
    }

    @Test(dataProvider = "files")
    public void verifyLoadImageLogo(String fileName) {
        NewObjectPage newObjectPage = new NewObjectPage(driver, log).openPage();
        newObjectPage.selectVariantAddLogo("load",fileName);
        Assert.assertEquals(newObjectPage.getUploadedFilesNames(), fileName);

    }

    @Test(dataProvider = "getDataNewObject")
    public void inputDataFirstBlock(TestData data) {
        NewObjectPage newObjectPage = new NewObjectPage(driver, log).openPage();

        newObjectPage.type(data.getOwnerName(), newObjectPage.ownerField);
        newObjectPage.type(data.getEngineerName(), newObjectPage.firstEngineerField);
        newObjectPage.type(data.getManagerName(), newObjectPage.managerField);
        newObjectPage.type(data.getObjectName(), newObjectPage.nameObjectInput);
        newObjectPage.type(data.getObjectAddress(), newObjectPage.addressObjectInput);
        newObjectPage.type(data.getObjectURL(), newObjectPage.urlObjectInput);
        newObjectPage.type(data.getUsername(), newObjectPage.loginInput);
        newObjectPage.type(data.getPassword(), newObjectPage.passwordInput);
        newObjectPage.click(newObjectPage.checkBoxAuth);
        newObjectPage.selectVariantAddLogo(data.getLogoVariant(),data.getPathToFile());
        newObjectPage.type(data.getSnowCover(), newObjectPage.snowCoverInput);
        newObjectPage.click(newObjectPage.buttonSaveOwnerBlock);
        Assert.assertEquals(newObjectPage.find(newObjectPage.selectMessage(data.getLocator())).getText(), data.getMessageText());

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

    @DataProvider(name = "files")
    protected static Object[][] files() {
        return new Object[][]{
                {"oneMore.jpg"},
                {"twoMore.jpg"},
                {"threeMore.png"}
        };
    }
}
