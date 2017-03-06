package com.azwebdesign;

import com.azwebdesign.page.ListPage;
import com.azwebdesign.page.SubmitPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ListPageSteps {

    private WebDriver driver;
    List<WebElement> things;

    @Autowired
    public void setDriver(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
    }

    @When("^I gather all things posted to the page$")
    public void getAllThings() {
        ListPage listPage = new ListPage(driver);
        things = listPage.getThings();
    }

    @Then("^I should see a post with title containing (.*)$")
    public void titleContains(String titleContains) {
        boolean found = false;

        for(WebElement thing : things) {
            WebElement title = thing.findElement(By.className("title"));
            if (title.getText().contains(titleContains)) {
                found = true;
                break;
            }
        }
        if (!found) Assert.fail();
    }

    @When("^I click submit new link$")
    public void clickSubmitLink() {
        ListPage listPage = new ListPage(driver);
        listPage.getSubmitLink().click();

        // wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains(new SubmitPage(driver).getUrl()));
    }
}
