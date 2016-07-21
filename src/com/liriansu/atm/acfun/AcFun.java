package com.liriansu.atm.acfun;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * {@link AcFun} is a builder for {@link AC}
 */
public class AcFun {
    private final ChromeDriverService chromeService;
    private       WebDriver           driver;
    private       Wait<WebDriver>     wait;

    private final ExpectedCondition<Boolean> acLoaded = new ExpectedCondition<Boolean>() {
        @Override
        public Boolean apply(WebDriver input) {
            // TODO log here say loading acfun page
            return driver.findElement(By.id("area-player")) != null;
        }
    };

    public AcFun(String driverPath) throws IOException {
        System.setProperty("webdriver.chrome.driver", driverPath);
        chromeService = new ChromeDriverService.Builder().usingDriverExecutable(new File(driverPath)).usingAnyFreePort().build();
        chromeService.start();
        driver = new RemoteWebDriver(chromeService.getUrl(), DesiredCapabilities.chrome());
        driver.manage().window().setPosition(new Point(-2000, 0));
        wait = new WebDriverWait(driver, 30);
    }

    public void stop() {
        if (null != driver) driver.quit();
        if (null != chromeService) chromeService.stop();
    }

    public AC get(long id) {
        String url = String.format("http://www.acfun.tv/v/ac%s", id);
        // TODO set protocol and domain
        driver.get(url);
        wait.until(acLoaded);
        String html    = driver.findElement(By.tagName("body")).getText();
        String article = driver.findElement(By.id("area-player")).getText();
        return new AC(id, html, article);
    }
}
