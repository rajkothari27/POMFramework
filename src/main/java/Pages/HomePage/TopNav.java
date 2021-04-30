package Pages.HomePage;


import ExcelData.ExcelUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TopNav {
    Logger logger = LogManager.getLogger(TopNav.class);
    public String url = "https://zoom.us/";

    public TopNav(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get(url);
    }

    @FindBy(xpath = "//ul[@class='list-inline pull-right']/li[4]/a")
    public WebElement support;
    @FindBy(id = "resources")
    public WebElement resource;
    @FindBy(xpath = "//ul[@id=\"resourcesDropdown\"]/li/a")
    public List<WebElement> resourceList;
    @FindBy(xpath = "//*[@id=\"black-topbar\"]/div/ul/li[2]/a")
    public WebElement contact;
    //request a demo page element below
    @FindBy(xpath = "//*[@id=\"black-topbar\"]/div/ul/li[1]/a")
    public WebElement demo;
    @FindBy(xpath = "//*[@id=\"livedemo_content\"]/div/div[1]/div/a[1]")
    public WebElement mainVideo;
    @FindBy(xpath = "/html/body/div[10]/div/div/div/div/div[2]/div/div/iframe")
    public WebElement iframe;
    @FindBy(xpath = "//*[@id=\"movie_player\"]/div[4]/button")
    public WebElement videoPlayButton;
    @FindBy(xpath = "//*[@id=\"video_dialog\"]/div/div/div[1]/button")
    public WebElement videoCloseButton;
    @FindBy(id = "email")
    public WebElement email;
    @FindBy(id = "company")
    public WebElement company;
    @FindBy(id = "first_name")
    public WebElement firstName;
    @FindBy(id = "last_name")
    public WebElement lastName;
    @FindBy(id = "00Nd0000007MFAl")
    public Select employee;
    @FindBy(id = "00Nd0000007MFAl")
    public WebElement employeeDropDown;
    @FindBy(id = "phone")
    public WebElement phone;
    @FindBy(id = "country")
    public WebElement countryDropDown;
    @FindBy(id = "country")
    public Select country;
    @FindBy(id = "state")
    public WebElement stateDropDown;
    @FindBy(id = "state")
    public Select state;
    @FindBy(id = "zipcode")
    public WebElement zip;
    @FindBy(id = "description")
    public WebElement additionalInfo;
    @FindBy(id = "gdpr-optout")
    public WebElement radioButtonNo;
    @FindBy(id = "btnSubmit")
    public WebElement submitButton;

    public TopNav(){
        super();
    }

    public void resourceListMenu(WebDriver driver) {
        for (int i = 0; i < resourceList.size(); i++) {
            Actions actions = new Actions(driver);
            actions.moveToElement(resource).build().perform();
            actions.moveToElement(resourceList.get(i))
                    .contextClick(resourceList.get(i))
                    .keyDown(Keys.CONTROL)
                    .click(resourceList.get(i)).perform();
            driver.switchTo().parentFrame();
        }
    }

    public void switchingWindows(WebDriver driver) {
        Set<String> windowIds = driver.getWindowHandles(); // Set not allows duplicate values
        Object[] array = windowIds.toArray();
        String parent = driver.getWindowHandle();
        Iterator<String> I1 = windowIds.iterator();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < array.length; i++) {
            logger.info("Current window : " + driver.getTitle());
            driver.switchTo().window((String) array[i]);
            //js.executeScript("window.scrollBy(0,5000)");
            logger.info("Next Window : " + driver.getTitle());
        }
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                driver.close();
            }
        }
        //switch to the parent window
        driver.switchTo().window(parent);
        logger.info("parent window :  " + driver.getTitle());
    }

    public void resourceSameScreenCheck(WebDriver driver) {
        for (int i = 0; i < resourceList.size(); i++) {
            Actions actions = new Actions(driver);
            actions.moveToElement(resource).build().perform();
            resourceList.get(i).click();
            driver.navigate().back();
        }
        driver.get(url);
    }

    public void closeAlert(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        alert.accept();
    }

    public void checkResourceText(WebDriver driver) {
        String[] ActualText = {"Download Zoom Client", "Video Tutorials", "Live Training",
                "Webinars and Events", "Zoom Blog", "FAQ",
                "Privacy and Security", "Zoom Virtual Backgrounds", "COVID-19 Resources"};
        for (int i = 0; i < resourceList.size(); i++) {
            Actions actions = new Actions(driver);
            actions.moveToElement(resource).build().perform();
            String text = resourceList.get(i).getText();
            logger.info("Actual text : " + ActualText[i]);
            logger.info("Link text   : " + text);
            Assert.assertEquals(ActualText[i], text);
        }

    }

    public void simpleDemoFormCheck(WebDriver driver) throws InterruptedException {
        demo.click();
        mainVideo.click();
        driver.switchTo().frame(iframe);
        videoPlayButton.click();
        Thread.sleep(8000);
        driver.switchTo().defaultContent();
        videoCloseButton.click();
        email.sendKeys("kothariraj55@gmail.com");
        company.sendKeys("kothari");
        firstName.sendKeys("Raj");
        lastName.sendKeys("Kothari");
        employeeDropDown.click();
        employee = new Select(driver.findElement(By.id("00Nd0000007MFAl")));
        employee.selectByIndex(2);
        phone.sendKeys("6478041165");
        countryDropDown.click();
        country = new Select(driver.findElement(By.id("country")));
        country.selectByValue("CA");
        state = new Select(driver.findElement(By.id("state")));
        stateDropDown.click();
        state.selectByVisibleText("Ontario");
        zip.sendKeys("L5B 4P2");
        additionalInfo.sendKeys("hi my name is raj");
        radioButtonNo.click();
        submitButton.click();
    }

    @DataProvider(name = "demoPageData")
    public Object[][] checkDemoFormByDataprovider() {
        Object[][] data={{"shims@gmail.com","shims","shimoli","patel","1234567891"},
                {"raj@gmail.com","raj","raj","koth","1234567891"},
                {"blaze@gmail.com","blaze","blaze","blaze","1234567891"}};
        return data;
    }

    public void fillFormByData(WebDriver driver,
                               String demail,
                               String dcompanyName,
                               String dfirstName,
                               String dlastName,
                               String dphone) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        demo.click();
        mainVideo.click();
        driver.switchTo().frame(iframe);
        videoPlayButton.click();
        Thread.sleep(8000);
        driver.switchTo().defaultContent();
        videoCloseButton.click();
        email.sendKeys(demail);
        company.sendKeys(dcompanyName);
        firstName.sendKeys(dfirstName);
        lastName.sendKeys(dlastName);
        employeeDropDown.click();
        employee = new Select(driver.findElement(By.id("00Nd0000007MFAl")));
        employee.selectByIndex(2);
        phone.sendKeys(dphone);
        countryDropDown.click();
        country = new Select(driver.findElement(By.id("country")));
        country.selectByValue("CA");
        state = new Select(driver.findElement(By.id("state")));
        stateDropDown.click();
        state.selectByVisibleText("Ontario");
        zip.sendKeys("L5B 4P2");
        additionalInfo.sendKeys("hi my name is raj");
        radioButtonNo.click();
        submitButton.click();
    }

    @DataProvider(name = "demoExcelData")
    public Object[][] excelData() throws IOException {
        String path="src/main/java/ExcelData/data.xlsx";
        String sheet = "UserDemoData";
        ExcelUtils excelUtils = new ExcelUtils(path);
        excelUtils.getRowCount(sheet);
        int totalRows = excelUtils.getRowCount(sheet);
        int totalColumns = excelUtils.getCellCount(sheet,1);

        String [][] formData = new String[totalRows][totalColumns];
        //starting loop from 1 because 0 will be header of excel file and we don't need it for row
        //i represent row
        //j represent column
        for (int i = 1; i <=totalRows ; i++) {
            for (int j = 0; j < totalColumns; j++) {
                formData[i-1][j]=excelUtils.getCellData(sheet,i,j);

            }
        }
        return formData;

    }

}

