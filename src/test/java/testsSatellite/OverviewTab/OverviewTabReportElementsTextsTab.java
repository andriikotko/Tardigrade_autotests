package testsSatellite.OverviewTab;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.SystemParams;
import pages.Tabs.OverviewTab_Details;
import pages.Tabs.OverviewTab_Report;

import java.util.concurrent.TimeUnit;

public class OverviewTabReportElementsTextsTab {

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
    public void overviewReportElementsTextsTest () {

        OverviewTab_Report overviewTabReport = PageFactory.initElements(driver, OverviewTab_Report.class);

        Assert.assertEquals(overviewTabReport.reportTabHeader.getText(), "Report");
        Assert.assertEquals(overviewTabReport.currentBillingPeriod.getText(), "Current Billing Period");
        Assert.assertEquals(overviewTabReport.previousBillingPeriod.getText(), "Previous Billing Period");
        Assert.assertEquals(overviewTabReport.customDateRange.getText(), "Custom Date Range");
        Assert.assertEquals(overviewTabReport.storageHeader.getText(), "Storage, TB x Hours");
        Assert.assertEquals(overviewTabReport.egressHeader.getText(), "Egress, GB");
        Assert.assertEquals(overviewTabReport.objectHoursHeader.getText(), "Object Hours");
        Assert.assertTrue(overviewTabReport.currentRollUpPeriod.getText().startsWith("Roll Up Period"));
        Assert.assertEquals(overviewTabReport.downloadAdvancedReportHeader.getText(), "Download Advanced Report");

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
