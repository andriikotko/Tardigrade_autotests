package testsSatellite;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RemoteWebDriverCreation;

import java.net.MalformedURLException;

public class withRemoteDriver {
    RemoteWebDriver driver;
    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        RemoteWebDriverCreation rd = new RemoteWebDriverCreation(driver);
        driver= rd.RemoteChromeDriver();
        driver.get("http://localhost:10002/login");
        System.out.println(driver.getTitle());

    }
    @Test
    public void elemntsTextsTest(){
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        Assert.assertEquals(loginPage.loginFormHeader.getText(), "Login to Tardigrade");
        Assert.assertEquals(loginPage.footerLogoText.getText(), "Storj Labs Inc 2019.");
        Assert.assertEquals(loginPage.btnCreateAccount.getText(),"Create Account");
        Assert.assertEquals(loginPage.btn_Login.getText(), "Log In");
        Assert.assertEquals(loginPage.linkForgotPassword.getText(), "Forgot password?");
        Assert.assertEquals(loginPage.linkToTermsAndConditions.getText(),"Terms & Conditions");
        Assert.assertEquals(loginPage.linkToSupport.getText(), "Support");
        Assert.assertTrue(loginPage.userNameField.getAttribute("placeholder").endsWith("Email"));
        Assert.assertTrue(loginPage.passwordField.getAttribute("placeholder").endsWith("Password"));
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
