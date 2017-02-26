Meta:

Narrative:
As a category user
I want to try login with a wrong user id
So that I get an error message as Email or Password is incorrect.

Scenario: scenario description
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0}

Then I validate following field is present ERROR MSG


Examples:
|User_Name              |Password       |
|abhishek.verma02@snapdeal.com  |123456    |
