package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.lang.reflect.Method;

@Listeners({ base.TestListener.class })
public class BaseTest {
	protected WebDriver driver;
	protected Logger log;

	String testSuiteName;
	String testName;
	String testMethodName;

	@Parameters({ "browser", "chromeProfile", "deviceName" })
	@BeforeMethod(alwaysRun = true)
	public void setUp(Method method, @Optional("chrome") String browser, @Optional String profile,
			@Optional String deviceName, ITestContext ctx) {
		 testName = ctx.getCurrentXmlTest().getName();
		log = LogManager.getLogger(testName);

		BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
		if (profile != null) {
			driver = factory.createChromeWithProfile(profile);
		} else if (deviceName != null) {
			driver = factory.createChromeWithMobileEmulation(deviceName);
		} else {
			driver = factory.createDriver();
		}
		driver.manage().window().maximize();
		this.testSuiteName = ctx.getSuite().getName();
		this.testMethodName = method.getName();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		log.info("Close driver");
		// Close browser
		driver.quit();
	}

}
