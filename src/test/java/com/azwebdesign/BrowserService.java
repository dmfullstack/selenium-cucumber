package com.azwebdesign;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SeleniumApplication.class)
public class BrowserService {

    private WebDriver driver;

    @Autowired
    public void setDriver(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
    }

    @When("^I go to (.*)$")
    public void goTo(String address) {
        driver.get(address);

        // wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("body"))));
    }

    @Then("^I should see a page with title containing (.*)$")
    public void titleContains(String titleContains) {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains(titleContains));
    }
}
