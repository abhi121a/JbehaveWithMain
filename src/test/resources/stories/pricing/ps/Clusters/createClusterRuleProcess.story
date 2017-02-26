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
Then I click cluster's CREATE CLUSTER BUTTON
Then I Validate cluster's CLUSTER TEXT in field <clustername>
Then I click on PRICE RULES
And I click cluster's RULES CLUSTER
And I Validate cluster's CLUSTER RULE CREATED in field <clustername>
When I type cluster's <clustername> in the field CLUSTER CONSTRAINS with values <Cluster values>

When I type cluster's <CM> in the field CM RULES
And I type cluster's <GM> in the field GM RULES
And I type cluster's <CD> in the field COMPETITOR DIFFERENCE
And I type cluster's <MD> in the field MAX DIFFERENCE
And I type cluster's <NC> in the field CM RULE NO COMPETITOR
And I type cluster's <NCGM> in the field GM RULE NO COMPETITOR
Then I click cluster UPDATE CLUSTER RULE and check for alert message To process your changes please click PROCESS button at subcategory page
Then I Click PriceRules button SUBCATEGORY
Then I click the PROCESS button and check for alert message Please click on Apply Cashback BUTTON in view Summary Page to apply changes.
Then Check for Message PROCESS_CONFIRMATION_MSG
When I click on the button VIEW SUMMARY BUTTON

!--UPDATE CLUSTER RULE
!-- Then I validate My Sql DB results using query SelectRulesException
!-- able to create a cluster need to validate value of that cluster.




Examples:
|User_Name                      |Password       |CATEGORY_NAME               |SUBCAT              |supc   |clustername    |Cluster values     |
|abhishek.verma02@snapdeal.com  |12345          |FASHION JEWELLERY      |PEARL JEWELLERY     |10014    |cl_0001    |8_8_8_8_8_8    |