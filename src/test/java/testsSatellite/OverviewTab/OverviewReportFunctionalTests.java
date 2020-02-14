package testsSatellite.OverviewTab;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.SystemParams;
import pages.Tabs.OverviewTab_Details;
import pages.Tabs.OverviewTab_Report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class OverviewReportFunctionalTests {
    public WebDriver driver;
    @BeforeMethod
    @Parameters("browser")
    public void setUp( @Optional("Chrome") String browser) throws Exception {

        String OS = System.getProperty("os.name");
        String chosingOS = "";
        if (OS.equals("Linux")) {
            switch (browser) {
                case "Firefox":
                    chosingOS = SystemParams.GECKODRIVERPATH;
                    System.setProperty("webdriver.gecko.driver", chosingOS);
                    driver = new FirefoxDriver();
                    break;
                case "Chrome":
                    chosingOS = SystemParams.CHROMEDRIVERPATH;
                    System.setProperty("webdriver.chrome.driver", chosingOS);
                    driver = new ChromeDriver();
                    break;
                case "Opera":
                    chosingOS = SystemParams.OPERADRIVERPATH;
                    System.setProperty("webdriver.opera.driver", chosingOS);
                    driver = new OperaDriver();
                    break;

                default:
                    chosingOS = "";
            }
        } else if (OS.substring(0, 4).equals("Windo")) {
            switch (browser) {
                case "Firefox":
                    chosingOS = SystemParams.GECKODRIVERPATHWIN;
                    System.setProperty("webdriver.gecko.driver", chosingOS);
                    driver = new FirefoxDriver();
                    break;
                case "Chrome":
                    chosingOS = SystemParams.CHROMEDRIVERPATHWIN;
                    System.setProperty("webdriver.chrome.driver", chosingOS);
                    driver = new ChromeDriver();
                    break;
                case "Opera":
                    chosingOS = SystemParams.OPERADRIVERPATHWIN;
                    System.setProperty("webdriver.opera.driver", chosingOS);
                    driver = new OperaDriver();
                    break;
                default:
                    chosingOS = "";
            }
        } else if (OS.substring(0, 3).equals("Mac")) {
            switch (browser) {
                case "Firefox":
                    chosingOS = SystemParams.GECKODRIVERPATH;
                    System.setProperty("webdriver.gecko.driver", chosingOS);
                    driver = new FirefoxDriver();
                    break;
                case "Chrome":
                    chosingOS = SystemParams.CHROMEDRIVERPATHMAC;
                    System.setProperty("webdriver.chrome.driver", chosingOS);
                    driver = new ChromeDriver();
                    break;
                case "Opera":
                    chosingOS = SystemParams.OPERADRIVERPATHWIN;
                    System.setProperty("webdriver.opera.driver", chosingOS);
                    driver = new OperaDriver();
                    break;
                case "Safari":
                    chosingOS = SystemParams.SAFARIDRIVERPATHMAC;
                    System.setProperty("webdriver.safari.driver", chosingOS);
                    driver = new SafariDriver();
                    break;
                default:
                    chosingOS = "";
            }

        } else {
            //If no os passed throw exception
            throw new Exception("os is not correct");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(HomePage.HOMEURL);

        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.loginToAccountWithValidCreds(homePage.ACCOUNT, homePage.PASSWORD);
        homePage.overview_tab.click();


        OverviewTab_Details overviewTabDetails = PageFactory.initElements(driver, OverviewTab_Details.class);
        overviewTabDetails.reportTab.click();

    }

    @Test
    public void overviewReportAdvancedReportLinkTest () throws Exception {

        OverviewTab_Report overviewTabReport = PageFactory.initElements(driver, OverviewTab_Report.class);
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);

        overviewTabReport.downloadAdvancedReportButton.click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
//        driver.close();
//        driver.switchTo().window(tabs2.get(0));

        Assert.assertTrue(driver.getCurrentUrl().startsWith("http://localhost:"+ (homePage.satellitePort()) +"/usage-report?projectID="));

    }

    @Test
    public void overviewReportAdvancedReportTest () {

        OverviewTab_Report overviewTabReport = PageFactory.initElements(driver, OverviewTab_Report.class);

        overviewTabReport.downloadAdvancedReportButton.click();

        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
//        driver.close();
//        driver.switchTo().window(tabs2.get(0));

        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/table/thead/tr[1]/th[1]")).getText() ,"Bucket Name");

    }

    @Test
    public void overviewReportTabCalendarVisibilityTest (){
        OverviewTab_Report overviewTabReport = PageFactory.initElements(driver, OverviewTab_Report.class);

        overviewTabReport.customDateRange.click();

        WebDriverWait wait = new WebDriverWait(driver,10);

        wait.until(ExpectedConditions.visibilityOf(overviewTabReport.calendarForCustomDataRange));

        Assert.assertTrue(overviewTabReport.calendarForCustomDataRange.isDisplayed());
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
