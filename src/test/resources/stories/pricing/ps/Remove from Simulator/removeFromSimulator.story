Meta:

Narrative:
As a user
I want to remove SUPC-Vendor(s) from PS
and validate its status in PS_user_input table
Status should be set to 0 after addition of list to PS.
Status should be reset to previous state when deleted from PS.


Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}

When I click on link PRODUCT CONFIG
When I click on link SET RFS

When Upload file from <location> after click button BROWSE file
Then I click the UPLOAD button and check for alert message Success
Then I validate My Sql DB results for remove from simulator using query SelectFromPSUserInputforSUPC with <SUPC> if on
When I click on DELETE button on rfs page for <SUPC>
Then I validate My Sql DB results for remove from simulator using query SelectFromPSUserInputforSUPC with <SUPC> if off

Examples:
|User_Name                     |Password       |SUPC      |CATEGORY_NAME       |SUBCATEGORY_NAME   |location                    |
|sakshi.mahendru@snapdeal.com  |12345          |1010285   |Bags & Luggage      |Luggage    |/home/sakshi/Downloads/rfs_example.xlsx