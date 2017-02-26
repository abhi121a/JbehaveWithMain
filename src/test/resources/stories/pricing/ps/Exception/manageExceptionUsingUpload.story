Meta:

Narrative:
As a user
I want to login and add an product exception to a sub category
So that I can set a rule for the product achieve a business goal

Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}
me>
!-- validating login page
!-- Now selecting a subcat for creating a exception for it
When I click on link PRODUCT CONFIG
And I click on link MANAGE PRODUCT
!-- Adding an exception using manage page Upload button
Then Upload file from location <location> after click button CHOOSE file
And I clicked button UPLOAD
And I validated value in ADDEDEXCEPTION for <SUPC>
And I click homepage PRICE RULES
And I click on EXCEPTIONS

Examples:
|User_Name                      |Password       |SUPC       |SUB_CAT      |location                                              |
|abhishek.verma02@snapdeal.com  |12345          |10014      |43           |C:\Users\abhishek.verma02\Desktop\productlist.xlsx    |
