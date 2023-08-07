package org.example.uiwesbank.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.uiwesbank.pages.CalculatePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class CalculatePageSteps {
    private final CalculatePage calculatePage;
    WebDriver driver;

    public CalculatePageSteps() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.wesbank.co.za/home/calculate");
        this.calculatePage = new CalculatePage(driver);
    }

    @Given("the vehicle purchase price is R {string}")
    public void theVehiclePurchasePriceIsR(String price) {
        calculatePage.enterVehiclePrice(price);
    }

    @Given("the deposit is R {string}")
    public void theDepositIsR(String deposit) {
        calculatePage.enterDepositAmount(deposit);
    }

    @Given("the payment term is {string} months")
    public void thePaymentTermIsMonths(String months) {
        calculatePage.selectPaymentTerm(months);
    }

    @Given("the balloon payment is {string} %")
    public void theBalloonPaymentIs(String percentage) {
        calculatePage.enterBalloonPayment(percentage);
    }

    @Given("the interest rate is {string} %")
    public void theInterestRateIs(String rate) {
        calculatePage.enterInterestRate(rate);
    }

    @When("the user calculates")
    public void theUserCalculatesTheMonthlyRepayment() {
    }

    @Then("the monthly repayment is {string}")
    public void theMonthlyRepaymentIs(String repayments) {
        assertTrue(calculatePage.isMonthlyRepaymentEqualTO(repayments));
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    @Then("the amount to finace is {string}")
    public void theAmountToFinaceIs(String amountToFinace) {
        assertTrue(calculatePage.isAmountToFinanceEqualTO(amountToFinace));
    }

    @Then("the interest amount is {string}")
    public void theInterestAmountIs(String interestAmount) {
        assertTrue(calculatePage.isInterestAmountEqualTO(interestAmount));
    }

    @Then("the cost of credit is {string}")
    public void theCostOfCreditIs(String costOfCredit) {
        assertTrue(calculatePage.isTotalCostOfCreditEqualTO(costOfCredit));
    }
}