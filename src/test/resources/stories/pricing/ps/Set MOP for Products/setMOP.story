Meta:

Narrative:
As a user
I want to set the Minimum Offer Price (MOP) for SUPC from PS under
Product Configuration and validate the result in
ps_mop_list table. MOP for the SUPC(s)
should be same set by user.

Scenario: scenario description
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}


When I click on link PRODUCT CONFIG
When I click on link SET MOP
When Upload file from location after click button BROWSE file
Then I click the UPLOAD button and check for alert message Success
Then I validate My Sql DB results for set mop for products using query SelectFromPsMOPList with <SUPC> if on with mop
When I click on DELETE button on rfs page for <SUPC>
Then I validate My Sql DB results for set mop for products using query SelectFromPsMOPList with <SUPC> if off with mop

Examples:
|User_Name                     |Password       |SUPC    |mop |CATEGORY_NAME       |SUBCATEGORY_NAME   |location
|sakshi.mahendru@snapdeal.com  |snap@1234      |1015487 |1000|Bags & Luggage      |Luggage   |/home/sakshi/Downloads/mop.xlsx