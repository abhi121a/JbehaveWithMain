Meta:

Narrative:
As a user manageExceptionTextBox
I want to login and add an product exception to a sub category
So that I can set a rule for the product achieve a business goal

Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}


!-- validating login page
!-- Now selecting a subcat for creating a exception for it

When I click on link PRODUCT CONFIG
And I click on link MANAGE PRODUCT
!-- Adding an exception using manage page text box
When In Exception page I type <SUPC> in field TEXTBOX
Then clicked button ADD_BUTTON and check for alert message Success
And I validated value in ADDEDEXCEPTION for <SUPC>



Examples:
|User_Name                      |Password       |CATEGORY       |SUBCAT     |SUPC       |
|abhishek.verma02@snapdeal.com  |12345  |FASHION JEWELLERY |PEARL JEWELLERY     |10014
