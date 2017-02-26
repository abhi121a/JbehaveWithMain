Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}
Then I click on PRODUCT CONFIG
And I click on MANAGE CLUSTER
Then I click cluster's CREATE CLUSTER
When I type cluster's <clustername> in the field CLUSTER NAME
And I select cluster's dropdown for SUPCTYPE with Selective
!-- Need to updaload a file from lhere
Then Upload Cluster's file from location <location> after click button CHOOSEFILE file
And I click cluster's CREATE CLUSTER BUTTON
And I Validate cluster's CLUSTER TEXT in field <clustername>
And I click on PRICE RULES
And I click cluster's RULES CLUSTER
And I Validate cluster's CLUSTER RULE CREATED in field <clustername>


!-- Need to update Selective to all if needed to select All from drop down.
!-- Need to change the location path as it is hard coded it should be present in the code



Examples:
|User_Name                      |Password       |CATEGORY_NAME          |SUBCAT           |clustername    |location     |
|abhishek.verma02@snapdeal.com  |Nov@2015       |Bags & Luggage         |Luggage          |cl_0001        |C:\Users\abhishek.verma02\Desktop\productlist.xlsx              |
