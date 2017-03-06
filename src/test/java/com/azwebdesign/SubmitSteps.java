package com.azwebdesign;

import com.azwebdesign.page.PostPage;
import com.azwebdesign.page.SubmitPage;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

public class SubmitSteps {

    private WebDriver driver;

    @Autowired
    public void setDriver(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
    }

    @When("^I post the link (.*) to the (.*) subreddit with title (.*)$")
    public void postLink(String link, String subredditName, String title) {
        SubmitPage submitPage = new SubmitPage(driver);
        try {
            URI uri = new URI(link);
        } catch (URISyntaxException e) {
            Assert.fail("Invalid URI: " + link + "\n" + e.getMessage());
        }

        submitPage.getUrlInput().sendKeys(link);
        submitPage.getSubreddit().sendKeys(subredditName);
        submitPage.getTitle().sendKeys(title);
        submitPage.getSubmit().click();

        // wait for page to load
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @When("^I post the link (.*) to the (.*) subreddit$")
    public void postLink(String link, String subredditName) {
        SubmitPage submitPage = new SubmitPage(driver);
        try {
            URI uri = new URI(link);
        } catch (URISyntaxException e) {
            Assert.fail("Invalid URI: " + link + "\n" + e.getMessage());
        }

        submitPage.getUrlInput().sendKeys(link);
        submitPage.getSubreddit().sendKeys(subredditName);

        // wait for reddit to suggest title
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(submitPage.getSuggestedTitle()));
        submitPage.useSuggestedTitle();

        submitPage.getSubmit().click();

        // wait for page to load
        wait.until(ExpectedConditions.visibilityOf(new PostPage(driver).getParentComments().get(0)));
    }
}
