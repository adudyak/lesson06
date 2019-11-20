package com.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class XpathTest {
    // Instance of WebDriver
    private WebDriver driver;

    /**
     * Set up method
     */
    @Before
    public void setUp() {

        // If you want to disable infobars please use this code
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        // Initialize path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        // Initialize instance of ChromeDriver and add options
        driver = new ChromeDriver(options);

        // Set 10 second for implicitly wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Maximize window
        driver.manage().window().maximize();
    }

    /**
     * Open page, grab and compare prices
     *
     * @return
     */
    @Test
    public void testComparePricesTest() {
        //Prices xPath
        String currentPriceXpath = "(//div[.='Samsung Galaxy S6']/parent::div/following-sibling::div//span[contains(text(), '$')])[1]";
        String regularPriceXpath = "(//div[.='Samsung Galaxy S6']/parent::div/following-sibling::div//span[contains(text(), '$')])[2]";

        // Open page
        driver.get("https://supsystic.com/example/comparison-example/");

        // Grab prices, remove $, convert to double, compare
        double currentPrice = Double.parseDouble(driver.findElement(By.xpath(currentPriceXpath)).getAttribute("innerHTML").replace("$", ""));
        System.out.println("Current price is: " + currentPrice);
        double regularPrice = Double.parseDouble(driver.findElement(By.xpath(regularPriceXpath)).getAttribute("innerHTML").replace("$", ""));
        System.out.println("Regular price is: " + regularPrice);
        System.out.println("Difference is: " + (regularPrice - currentPrice));
    }

    @Test
    public void testSymbolsTest() {
        // Open page
        driver.get("https://unicode-table.com/ru/");
//        Пишем XPath который достает нам символ - Q из таблицы и выводим в консоль
        System.out.println(driver.findElement(By.xpath(dataCodeXpath(81))).getText());
//        Пишем XPath который достает нам символ - & из таблицы и выводим в консоль
        System.out.println(driver.findElement(By.xpath(dataCodeXpath(38))).getText());
//        Пишем XPath который достает нам символ - A из таблицы и выводим в консоль
        System.out.println(driver.findElement(By.xpath(dataCodeXpath(65))).getText());
    }

    /**
     * Returns xpath string with given dataCode
     *
     * @param dataCode
     * @return
     */
    public String dataCodeXpath(int dataCode) {
        return "//*[@data-code='" + dataCode + "']";
    }

    /**
     * After method, quit driver
     */
    @After
    public void tearDown() {

        // Quit from Driver. close() just close window,
        // quit() - close all window an driver
        driver.quit();
    }
}
