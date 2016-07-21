package com.liriansu.atm;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;

public class TestUnit {
    public static void main(String[] args) {
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        try {
            HtmlPage page = webClient.getPage("http://www.acfun.tv/a/ac2903169");
            System.out.println(page.asText());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\l9s\\workspace\\atm\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.manage().window().setPosition(new Point(-2000, 0));
//        WebDriver driver = new FirefoxDriver();
//
//        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
//        driver.get("http://www.acfun.tv/a/ac2903169");
//        String text = driver.findElement(By.id("area-player")).getText();
//        wait.until(new ExpectedCondition<Boolean>() {
//            @Override
//            public Boolean apply(WebDriver webDriver) {
//                return webDriver.findElement(By.id("area-player")) != null;
//            }
//        });
//        System.out.println(text);
//        driver.close();
    }
}
