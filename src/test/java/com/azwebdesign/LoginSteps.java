package com.azwebdesign;

import com.azwebdesign.page.ListPage;
import com.azwebdesign.utility.RedditConstants;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginSteps {

    private WebDriver driver;

    @Autowired
    BrowserService browserService;

    @Autowired
    public void setDriver(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
    }

    @Given("^I am logged in to reddit$")
    public void login() {
        if (!driver.getCurrentUrl().contains("reddit")) {
            browserService.goTo("http://www.reddit.com");
        }

        ListPage listPage = new ListPage(driver);
        if (listPage.getUsername().isDisplayed()) {
            // key in user name
            listPage.getUsername().sendKeys(RedditConstants.USER);
            // key in user password
            listPage.getPassword().sendKeys(RedditConstants.PASS);
            // click remember and submit buttons
            listPage.getRememberMe().click();
            listPage.getLoginSubmit().click();

            // wait for page to load
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(listPage.getLogout()));
        }
    }
}
