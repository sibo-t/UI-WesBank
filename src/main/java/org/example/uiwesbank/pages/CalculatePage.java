package org.example.uiwesbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalculatePage {
    WebDriver driver;
    By byVehiclePrice = By.xpath("//label[text()='Vehicle purchase price']/following-sibling::input[@id='wb-input-undefined']");
    By byDepositAmount= By.id("wol-ranger-1");
    By byPaymentTerm = By.xpath("//select[@class='wb-native-select-element']");
    By byBalloonPayment = By.id("wol-ranger-2");
    By byInterestRate = By.id("wol-ranger-0");
    By byMonthlyRepaymentAmount = By.xpath("//span[@class='output value']");
    By byMonthlyRepaymentCents = By.xpath("//span[@class='output sup change']");

    public CalculatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPaymentTerm(String months){
        WebElement selectElement = driver.findElement(byPaymentTerm);
        Select select = new Select(selectElement);
        select.selectByVisibleText(months);
    }

    public void enterVehiclePrice(String price){
        this.driver.findElement(byVehiclePrice).clear();
        this.driver.findElement(byVehiclePrice).sendKeys(price, Keys.ENTER);
    }

    public void enterDepositAmount(String price){
        this.driver.findElement(byDepositAmount).clear();
        this.driver.findElement(byDepositAmount).sendKeys(price, Keys.ENTER);
    }

    public void enterBalloonPayment(String percentage){
        this.driver.findElement(byBalloonPayment).clear();
        this.driver.findElement(byBalloonPayment).sendKeys(percentage, Keys.ENTER);
    }

    public void enterInterestRate(String rate){
        this.driver.findElement(byInterestRate).clear();
        this.driver.findElement(byInterestRate).sendKeys(rate, Keys.ENTER);
    }

    public boolean isMonthlyRepaymentEqualTO(String repayment){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            boolean value =  wait.until(ExpectedConditions.textToBePresentInElementLocated(byMonthlyRepaymentAmount, repayment.substring(0,repayment.length()-3)));
            boolean cents =  wait.until(ExpectedConditions.textToBePresentInElementLocated(byMonthlyRepaymentCents, repayment.substring(repayment.length()-3)));
            return value && cents;
        } catch (Exception e) {
            return false;
        }
    }

}
