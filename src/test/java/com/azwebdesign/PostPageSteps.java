package com.azwebdesign;

import com.azwebdesign.page.PostPage;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class PostPageSteps {

    private WebDriver driver;

    @Autowired
    public void setDriver(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
    }

    @Then("^I should see a reddit post page with title containing (.*)$")
    public void titleContains(String titleContains) {
        PostPage postPage = new PostPage(driver);

        if (!postPage.getTitle().getText().contains(titleContains)) {
            Assert.fail();
        }
    }

    @Then("^The post should have ([0-9]+) votes$")
    public void clickSubmitLink(int votes) {
        PostPage postPage = new PostPage(driver);
        if (!postPage.getLikes().getText().equals(new Integer(votes).toString())) {
            Assert.fail();
        }
    }
}
