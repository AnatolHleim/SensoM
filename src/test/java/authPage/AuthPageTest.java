package authPage;

import base.BaseTest;
import base.TestData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.logging.LogEntry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AuthPage;
import pages.MainPage;

import java.io.FileNotFoundException;
import base.TestUtilities;
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
    @Test
    public void jsErrorTest() {
        log.info("Starting jsErrorTest");
        SoftAssert softAssert = new SoftAssert();
        AuthPage authPage = new AuthPage(driver, log).openPage();
        authPage.type("asd@de.re",authPage.inputUserEmail);
        authPage.type("asd@de.re",authPage.inputPassword);
        authPage.click(authPage.buttonSubmit);
        List<LogEntry> logs = getBrowserLogs();

        // Verifying there are no JavaScript errors in console
        for (LogEntry logEntry : logs) {
            if (logEntry.getLevel().toString().equals("SEVERE")) {
                softAssert.fail("Severe error: " + logEntry.getMessage());
            }
        }
        softAssert.assertAll();
    }

    @Test(dataProvider = "getDataPositive", description = "PositiveAuthTest")
    public void positiveAuthTest(TestData data){

        log.info(data.getTestCase());
        AuthPage authPage = new AuthPage(driver, log).openPage();
        MainPage mainPage = authPage.logIn(data.getUsername(),data.getPassword());
        Assert.assertEquals(data.getMessageText(), mainPage.find(mainPage.welcome).getText());

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
}
