Meta:

Narrative:
As a user
I want to ENABLE and DISABLE the subcategory
and validate the status in ps_active_subcat table.
Status should be 1 for status ENABLE.
Status should be 0 for status DISABLE.

Scenario: scenario description
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}

!--Validate if subcat is enabled
Then I validate <Subcategory_Id> Status From My SQL using query SelectAllFromPSActiveSubcat with status ENABLE
!--validate all ps groups have status 1 or -1
Then I validate All PSGroups Status with <Subcategory_Id> From MY SQL using query SelectAllFromPSGroup status ENABLE
Then I click the DISABLE button for <SUBCATEGORY_NAME> and check for alert message Are you sure you want to disable this?

!-- Validate if subcat is disabled
Then I validate <Subcategory_Id> Status From My SQL using query SelectAllFromPSActiveSubcat with status DISABLE
!-- validate all ps groups have status 0 or -6 only
Then I validate All PSGroups Status with <Subcategory_Id> From MY SQL using query SelectAllFromPSGroup status DISABLE
Then I click the ENABLE button and check for alert message Are you sure you want to enable this?


Examples:
|User_Name                     |Password       |Subcategory_Id|CATEGORY_NAME       |SUBCATEGORY_NAME   |
|sakshi.mahendru@snapdeal.com  |      |32            |Bags & Luggage      |Luggage            |