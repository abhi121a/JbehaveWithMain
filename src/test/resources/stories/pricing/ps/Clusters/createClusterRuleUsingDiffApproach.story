Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: scenario description

GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}


When I click on link PRODUCT CONFIG
And I click on link MANAGE CLUSTER
Then I click cluster's CREATE CLUSTER
When I type cluster's <clustername> in the field CLUSTER NAME
Then I click cluster's CREATE CLUSTER BUTTON
Then I Validate cluster's CLUSTER TEXT in field <clustername>
Then I click on PRICE RULES
And I click cluster's RULES CLUSTER
And I Validate cluster's CLUSTER RULE CREATED in field <clustername>



Examples:
|User_Name                      |Password       |CATEGORY_NAME               |SUBCAT              |supc   |clustername    |Rules_value       |
|abhishek.verma02@snapdeal.com  |12345          |FASHION JEWELLERY      |PEARL JEWELLERY     |10014  |cl_0001        |3_3_2_40_2_2      |