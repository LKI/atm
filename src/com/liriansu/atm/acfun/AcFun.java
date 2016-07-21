package com.liriansu.atm.acfun;

import org.openqa.selenium.WebDriver;

/**
 * {@link AcFun} is a builder for {@link AC}
 */
public class AcFun {
    public AcFun() {
    }

    public AC get(long id) {
        // TODO maybe use chrome driver
        // TODO set protocol and domain
        String url = String.format("http://www.acfun.tv/v/ac%s", id);
        return null;
    }
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\l9s\\workspace\\atm\\chromedriver.exe");
//    //        WebDriver       driver = new HtmlUnitDriver(true);
//    WebDriver       driver = new ChromeDriver();
//    Wait<WebDriver> wait   = new WebDriverWait(driver, 30);
//        driver.get("http://www.acfun.tv/a/ac2903169");
//    String text = driver.findElement(By.id("area-player")).getText();
//        wait.until(new ExpectedCondition<Boolean>() {
//        @Override
//        public Boolean apply(WebDriver webDriver) {
//            System.out.println("Searching");
//            return webDriver.findElement(By.id("area-player")) != null;
//        }
//    });
//        System.out.println(text);
//        driver.close();
}
