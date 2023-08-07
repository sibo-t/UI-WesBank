package org.example.uiwesbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalculatePage {
    WebDriver driver;
    By byVehiclePrice = By.xpath("//label[text()='Vehicle purchase price']/following-sibling::input[@id='wb-input-undefined']");
    By byDepositAmount= By.xpath("//wol-ranger[@formcontrolname='deposit']/div/input");
    By byDepositAmountField= By.xpath("//label[text()=' Deposit amount ']/following-sibling::div");
    By byPaymentTerm = By.xpath("//select[@class='wb-native-select-element']");
    By byBalloonPayment = By.xpath("//wol-ranger[@formcontrolname='balloonpercentage']/div/input");
    By byBalloonPaymentField = By.xpath("//label[text()=' Balloon payment ']/following-sibling::div");
    By byInterestRate = By.xpath("//wol-ranger[@formcontrolname='interest']/div/input");
    By byInterestRateField = By.xpath("//label[text()=' Interest rate ']/following-sibling::div");
    By byMonthlyRepaymentAmount = By.xpath("//span[@class='output value']");
    By byMonthlyRepaymentCents = By.xpath("//span[@class='output sup change']");
    By byCloseCookieWindow = By.xpath("//div[@class='wb-ripple-host toast-close']/div[@class='close']");
    By byAmountToFinance = By.xpath("//div[@class='label' and text()='TOTAL COST OF CREDIT:']/following-sibling::div");
    By byInterestAmount = By.xpath("//div[@class='label' and text()='INTEREST AMOUNT:']/following-sibling::div");
    By byTotalCostOfCredit = By.xpath("//div[@class='label' and text()='TOTAL COST OF CREDIT:']/following-sibling::div");

    public CalculatePage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectPaymentTerm(String months){
        WebElement selectElement = driver.findElement(byPaymentTerm);
        Select select = new Select(selectElement);
        select.selectByVisibleText(months);
    }

    public void enterVehiclePrice(String price){
        click(byCloseCookieWindow);
        this.driver.findElement(byVehiclePrice).clear();
        this.driver.findElement(byVehiclePrice).sendKeys(price, Keys.ENTER);
    }

    public void enterDepositAmount(String price){
        click(byDepositAmountField);
        this.driver.findElement(byDepositAmount).sendKeys(price, Keys.ENTER);
    }

    public void enterBalloonPayment(String percentage){
        click(byBalloonPaymentField);
        this.driver.findElement(byBalloonPayment).sendKeys(percentage, Keys.ENTER);
    }

    public void enterInterestRate(String rate){
        click(byInterestRateField);
        this.driver.findElement(byInterestRate).sendKeys(Keys.BACK_SPACE);
        this.driver.findElement(byInterestRate).sendKeys(Keys.BACK_SPACE);
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

    public boolean isAmountToFinanceEqualTO(String amountToFinace){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            return wait.until(ExpectedConditions.textToBePresentInElementLocated(byAmountToFinance, amountToFinace));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isInterestAmountEqualTO(String interestAmount){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            return   wait.until(ExpectedConditions.textToBePresentInElementLocated(byInterestAmount, interestAmount));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTotalCostOfCreditEqualTO(String totalCostOfCredit){
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(45L));
            return   wait.until(ExpectedConditions.textToBePresentInElementLocated(byInterestAmount, totalCostOfCredit));
        } catch (Exception e) {
            return false;
        }
    }

    public void click(By byLocator) {
        boolean exceptionOccurred = false;
        int numberOfTries = 0;
        do {
            try {
                numberOfTries++;
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(byLocator));
                (new Actions(driver)).scrollToElement(element).moveToElement(element);
                wait.until(ExpectedConditions.visibilityOfElementLocated(byLocator)).click();
            } catch (Exception e) {
                exceptionOccurred = true;
            }
        } while (exceptionOccurred && numberOfTries < 3);
    }

}
