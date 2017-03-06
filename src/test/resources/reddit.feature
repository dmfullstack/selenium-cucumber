@smokeTest
Feature: To test that reddit post submission works fine.

#  Scenario: reddit submit new link
#
#    Given I am logged in to reddit
#    When I click submit new link
#    When I post the link http://rickhanton.com/?q=opinion/science-education-are-worthy-budget-goals to the bottesting subreddit
#    Then I should see a reddit post page with title containing education
#    Then The post should have 1 votes

  Scenario: reddit verify new link appears

    When I go to http://www.reddit.com/r/bottesting
    When I gather all things posted to the page
    Then I should see a post with title containing education