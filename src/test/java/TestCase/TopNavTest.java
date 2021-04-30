package TestCase;

import DriverManager.DriverManager;
import Pages.HomePage.TopNav;
import ProjectReports.ProjectReports;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.Priority;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class TopNavTest extends ProjectReports {
    public TopNav topNav;
    Logger logger = LogManager.getLogger(TopNavTest.class);

    @Parameters({"BrowserType"})
    @BeforeClass
    public void setUp(@Optional("chrome") String BrowserType) {
        logger.info("starting browser = " + " " + BrowserType);
        driverManager = new DriverManager();
        driverManager.setUp(BrowserType);
        topNav = new TopNav(driverManager.driver);
    }


    @Test(priority = 1)
    public void checkSupport() {
        logger.info("checking support link");
        Reporter.log("checking support link");
        test = extent.createTest("checking support link");
        String text = topNav.support.getText();
        Assert.assertEquals("SUPPORT", text);
        topNav.support.click();
        topNav.switchingWindows(driverManager.driver);
    }

    @Test(priority = 3)
    public void checkResourceNewWindow() {
        logger.info("checking resource drop down menu , opening links in new tab ");
        Reporter.log("checking resource drop down menu , opening links in new tab");
        test = extent.createTest("checking resource link on new window ");
        topNav.resourceListMenu(driverManager.driver);
        topNav.switchingWindows(driverManager.driver);
    }

    @Test(priority = 2)
    public void checkResource() {
        logger.info("checking resource drop down menu , opening links on same tab ");
        Reporter.log("checking resource drop down menu , opening links on same tab");
        test = extent.createTest("checking resource link on new window ");
        topNav.resourceSameScreenCheck(driverManager.driver);
        topNav.switchingWindows(driverManager.driver);
    }

    @Test(priority = 4)
    public void checkContact() {
        logger.info("checking contact alert");
        Reporter.log("checking contact alert");
        test = extent.createTest("checking contact ");
        topNav.contact.click();
        topNav.contact.sendKeys(Keys.ESCAPE);
    }

    @Test(priority = 5)
    public void checkResourceListText() {
        logger.info("checking resource link text ");
        Reporter.log("checking resource link text");
        test = extent.createTest("checking resource drop down text ");
        topNav.checkResourceText(driverManager.driver);
    }

    @Test(priority = 6)
    public void checkSimpleDemoForm() throws InterruptedException {
        logger.info("checking demo form by sending keys");
        Reporter.log("checking demo form by sending keys");
        test = extent.createTest("checking demo form ");
        topNav.simpleDemoFormCheck(driverManager.driver);
    }

    @Test(dataProvider = "demoPageData", dataProviderClass = TopNav.class, priority = 7)
    public void demoFormCheckDataProvider(String email, String companyName,
                                          String firstName,
                                          String lastName,
                                          String phone) throws InterruptedException {

        logger.info("checking demo form by data provider");
        Reporter.log("checking demo form by data provider");
        test = extent.createTest("checking demo form by data provider");
        topNav.fillFormByData(driverManager.driver,
                email,
                companyName,
                firstName,
                lastName,
                phone);


    }


    @Test(dataProvider = "demoExcelData", dataProviderClass = TopNav.class, priority = 8)
    public void demoFormCheckExcelData(String demoEmail, String demoCompanyName,
                                       String demoFirstName,
                                       String demoLastName,
                                       String demoPhone) throws InterruptedException {

        logger.info("checking demo form by data provider");
        Reporter.log("checking demo form by data provider");
        test = extent.createTest("checking demo form by data provider");
        topNav.fillFormByData(driverManager.driver,
                demoEmail,
                demoCompanyName,
                demoFirstName,
                demoLastName,
                demoPhone);


    }

    @AfterClass
    public void tearDown() {
        driverManager.driver.quit();
    }

}
