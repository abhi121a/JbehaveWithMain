Meta:

Narrative:
As a category user loginNLogoutCheck.story
I want to login to Price Simulator
So that I logout successfully


Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}

Then I validate that user navigate to <loginpage>


Examples:
|User_Name              |Password       |loginpage      |
|abhishek.verma02@snapdeal.com  |12345    |http://52.74.32.185:8080/PriceSimulator/viewSummary.action       |
