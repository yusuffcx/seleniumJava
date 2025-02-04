package org.example;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void loginTest(String url)
    {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebElement username = driver.findElement(By.id("username"));
        WebElement psw = driver.findElement(By.id("password"));
        WebElement pswXPath = driver.findElement(By.xpath("//input[@id='password']"));
        //WebElement logButton = driver.findElement(By.id("submit"));
        WebElement nearLogButton = driver.findElement(RelativeLocator.with(By.id("submit")).below(psw));
        System.out.println(nearLogButton);
        String LinkText = driver.findElement(By.partialLinkText("Test")).getText();
        System.out.println(LinkText);
        username.sendKeys("student");
        pswXPath.sendKeys("Password123");
        nearLogButton.click();
        System.out.println(driver.getCurrentUrl());
        String expUrl = driver.getCurrentUrl();
        WebElement test = driver.findElement(By.partialLinkText("Test Automation"));
        String actualUrl = "https://practicetestautomation.com/logged-in-successfully/";
        WebElement privacy = driver.findElement(RelativeLocator.with(By.partialLinkText("Privacy")).toRightOf(test));
        System.out.println(privacy.getText());
        Assert.assertEquals(actualUrl,expUrl);
    }

    public static void expPageCase1(String url){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebElement addFoodBtn = driver.findElement(By.id("add_btn"));
        WebElement row1 = driver.findElement(By.xpath("//div[@id='row1']"));
        System.out.println(row1);
        addFoodBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));
        WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']"));
        boolean flag = row2Input.isDisplayed();
        System.out.println("is row2 input being displayed? "+flag);
    }

    public static void expPageCase2(String url)
    {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebElement addFoodBtn = driver.findElement(By.id("add_btn"));
        addFoodBtn.click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='row2']/input[@class='input-field']")));

        WebElement input = driver.findElement(By.xpath("//div[@id='row2']/input[@class='input-field']"));
        input.sendKeys("text");

        List<WebElement> saveBtns = driver.findElements(By.name("Save"));
        for(WebElement button : saveBtns) {
            if(button.isDisplayed()) {
                button.click();
            }
        }
        WebElement savedText = driver.findElement(By.xpath("//div[@id='confirmation']"));
        if(savedText.isDisplayed())
        {
            System.out.println("The text was saved successfully");
        }
        
    }

    public static void main(String[] args) {
        //loginTest("https://practicetestautomation.com/practice-test-login/");
        expPageCase2("https://practicetestautomation.com/practice-test-exceptions/");

    }
}


