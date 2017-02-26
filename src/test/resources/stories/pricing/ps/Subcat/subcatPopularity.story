Meta:

Narrative:
As a user
I want to create a subcatpopularity rule
and validate the status in ps_group and ps_user_input table
which should be -1.

Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}

When I click on rule POPULARITY
When I select from dropdown for POPULARITY_BELOW_LIMIT with <below_value>
When I type values <popularity_below_values> in fields CM_GM_CD_MD_NC_NCGM in POPULARITY_BELOW_LIMIT_INPUTS
When I select from dropdown for POPULARITY_TILL_LIMIT with <till_value>
When I type values <popularity_till_values> in fields CM_GM_CD_MD_NC_NCGM in POPULARITY_TILL_LIMIT_INPUTS
When I type values <popularity_above_values> in fields CM_GM_CD_MD_NC_NCGM in POPULARITY_ABOVE_INPUTS
Then I click the UPDATE button and check for alert message To process your changes please click PROCESS button at subcategory page
Then Check for Message UPDATION_CONFIRMATION_MSG
Then I click the PROCESS button and check for alert message Please click on Apply Cashback BUTTON in view Summary Page to apply changes.
Then Check for Message PROCESS_CONFIRMATION_MSG
When I click on the button VIEW SUMMARY BUTTON
Then I validate My Sql DB results for subcatrule using query SelectAllFromPSGroupBeforeCashback with <subcatID>
Then I validate My Sql DB results for viewSummary using query SelectAllFromPSUserInputBeforeCashback


Examples:
|User_Name                     |Password       |popularity_below_values  |below_value|till_value |popularity_till_values|popularity_above_values|subcatID|CATEGORY_NAME       |SUBCATEGORY_NAME   |
|sakshi.mahendru@snapdeal.com  |12345        |3_3_2_5_2_2               |20        |100       |4_4_3_40_3_3            |5_5_4_60_3_3          |38     |Bags & Luggage      |Luggage    |