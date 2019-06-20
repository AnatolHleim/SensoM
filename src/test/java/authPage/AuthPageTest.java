package authPage;

import base.BaseTest;
import base.TestData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AuthPage;
import pages.MainPage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;


public class AuthPageTest extends BaseTest {
    @Test
    public void verifyTitlePage(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        Assert.assertEquals(authPage.getCurrentPageTitle(),"Client");
    }
    @Test
    public void verifyCurrentUrlPage(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        Assert.assertEquals(authPage.getCurrentUrl(),AuthPage.URL_AUTH);
    }
    @Test
    public void verifyShowPasswordOnClickEye(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("some", authPage.inputPassword);
        authPage.click(authPage.buttonShowPassword);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.inputPassword,"type","text"));
    }
    @Test
    public void verifyShowWaitMessageAfterTwoIncorrectInput(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.logIn("some@one.ru","sdsapassword");
        authPage.logIn("some@one.ru","passsword");
        Assert.assertTrue(authPage.find(authPage.messageBanFlex).isDisplayed());
    }
    @Test
    public void verifyInvisiblePasswordOnDoubleClickEye(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("some", authPage.inputPassword);
        authPage.click(authPage.buttonShowPassword);
        authPage.click(authPage.buttonShowPassword);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.inputPassword,"type","password"));
    }
    @Test
    public void verifyShowModalWindowRequisites(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.click(authPage.linkFooterRequisites);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.modalWindowRequisites,"class","requisites"));
    }
    @Test
    public void verifyCloseModalWindowRequisites(){
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.click(authPage.linkFooterRequisites);
        authPage.click(authPage.buttonCloseModalRequisites);
        Assert.assertTrue(authPage.elementHasAttribute(authPage.modalWindowRequisites,"class","requisites requisites_hidden"));
    }

    @Test(dataProvider = "getDataPositive", description = "PositiveAuthTest")
    public void positiveAuthTest(TestData data){
       // "добавить проверки для всех ролей": "привязка к локатору - добро пожаловать {user}"
        log.info(data.getTestCase());
        AuthPage authPage = new AuthPage(driver, log).openPage();
        MainPage mainPage = authPage.logIn(data.getUsername(),data.getPassword());
        Assert.assertEquals(data.getMessageText(), mainPage.find(mainPage.welcome).getText());

    }

    @Test(dataProvider = "getDataNegative", description = "NegativeAuthTest")
    public void negativeAuthTest(TestData data) {

        log.info(data.getTestCase());
        AuthPage authPage = new AuthPage(driver, log);
        authPage.openPage().
                logIn(data.getUsername(),data.getPassword());
        Assert.assertTrue(authPage.find(authPage.messageAboutIncorrectData).isDisplayed());
    }
    @Test(dataProvider = "getDataOptionalField", description = "NegativeAuthTest")
    public void emptyRequiredFieldTest(TestData data){

        log.info(data.getTestCase());
        AuthPage authPage = new AuthPage(driver, log);
        authPage.openPage().
                logIn(data.getUsername(),data.getPassword());
        Assert.assertTrue(authPage.elementHasAttribute(authPage.buttonSubmit,"class","button button_disabled"));

    }
    @DataProvider
    public Object[][] getDataPositive() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/positiveAuthData.json"));
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
    @DataProvider
    public Object[][] getDataNegative() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/negativeAuthData.json"));
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
    @DataProvider
    public Object[][] getDataOptionalField() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/resources/optionalEmptyAuthData.json"));
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
