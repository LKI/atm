package com.liriansu.atm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class TestUnit {
    public static void main(String[] args) throws IOException {
        String driverExe = TestUnit.class.getClass().getResource("/driver/chrome/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.driver", driverExe);
        ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverExe)).usingAnyFreePort().build();
        service.start();
        try {
            ChromeOptions options = new ChromeOptions();
            WebDriver driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
            try {
//            driver.manage().window().setPosition(new Point(-2000, 0));
                Wait<WebDriver> wait = new WebDriverWait(driver, 30);
                driver.get("http://www.acfun.tv/a/ac302412");
                driver.get("http://www.acfun.tv/a/ac2903169");
                String text = driver.findElement(By.id("area-player")).getText();
                wait.until(new ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.id("area-player")) != null;
                    }
                });
                System.out.println(text);
            } finally {
                driver.quit();
            }
        } finally {
            service.stop();
        }
    }
}
