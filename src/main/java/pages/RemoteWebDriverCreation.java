package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteWebDriverCreation {
    RemoteWebDriver driver;

    public RemoteWebDriverCreation(RemoteWebDriver driver) {
        this.driver=driver;
    }


    public RemoteWebDriver RemoteChromeDriver() throws MalformedURLException {
        DesiredCapabilities cap=DesiredCapabilities.chrome();
        URL u=new URL("http://localhost:4444/wd/hub");
        RemoteWebDriver driver=new RemoteWebDriver(u,cap);



        return driver;
    }
}
