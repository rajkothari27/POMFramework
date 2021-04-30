package DriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;


import java.util.concurrent.TimeUnit;

public class DriverManager {
    public WebDriver driver;
    public final static String CHROME="chrome";
    public final static String FIREFOX="firefox";
    public final static String IE="ie";
    public final static String OPERA="opera";



    public void setUp(String BrowserType) {
        if (BrowserType.equalsIgnoreCase(CHROME)) {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_win32/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--disable-notifications");
        } else if (BrowserType.equalsIgnoreCase(FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver-v0.29.0-win64/geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } else if (BrowserType.equalsIgnoreCase(IE)) {
            System.setProperty("webdriver.ie.driver", "Drivers/IEDriverServer_x64_3.150.1/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } else if (BrowserType.equalsIgnoreCase(OPERA)) {
            System.setProperty("webdriver.opera.driver", "Drivers/operadriver_win64/operadriver_win64/operadriver.exe");
            driver = new OperaDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        } else {
            System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver_win32/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            ChromeOptions opt = new ChromeOptions();
            opt.addArguments("--disable-notifications");
        }
    }

}
