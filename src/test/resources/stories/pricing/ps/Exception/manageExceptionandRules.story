Meta:

Narrative:
As a user
I want to login and add an product exception to a sub category
So that I can set a rule for the product achieve a business goal

Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}



!-- validating login page
!-- Now selecting a subcat for creating a exception for it
When I click on link PRODUCT CONFIG
When I click on link MANAGE PRODUCT
!-- Adding an exception using manage page
When In Exception page I type <SUPC> in field TEXTBOX
Then clicked button ADD_BUTTON and check for alert message Success
And I validated value in ADDEDEXCEPTION for <SUPC>
And I click homepage PRICE RULES
Then I Click PriceRules button EXCEPTIONS
And I validated value in PRODUCT SUPC for <SUPC>
When for a <SUPC> I type <OFFERPRICE> in field OFFER PRICE
And for a <SUPC> I type <CMLimit> in field CM LIMITS
Then for a <SUPC> I clicked button UPDATE and check for alert message To process your changes please click PROCESS button at subcategory page
Then I Click PriceRules button SUBCATEGORY
Then I click the PROCESS button and check for alert message Please click on Apply Cashback BUTTON in view Summary Page to apply changes.
Then Check for Message PROCESS_CONFIRMATION_MSG
When I click on the button VIEW SUMMARY BUTTON



Examples:
|User_Name                      |Password       |SUPC       |SUB_CAT    |OFFERPRICE  |CMLimit     |CATEGORY_NAME              |SUBCATEGORY_NAME            |
|abhishek.verma02@snapdeal.com  |12345          |1003101    |43         |3100        |10          |FASHION JEWELLERY      |PEARL JEWELLERY     |
