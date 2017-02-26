Meta:

Narrative:
As a category user
I want to create a cluster from manage cluster page and verify that it get created and displayed in Manage cluster page.
To verify that user can delete a cluter from manage page.

Scenario: scenario description
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}


Then I click on PRODUCT CONFIG
And I click on MANAGE CLUSTER
Then I click cluster's CREATE CLUSTER
When I type cluster's <clustername> in the field CLUSTER NAME
Then I click cluster's CREATE CLUSTER BUTTON
Then I Validate cluster's CLUSTER TEXT in field <clustername>
Then I click cluster's DELETE CLUSTER


Examples:
|User_Name                      |Password       |CATEGORY               |SUBCAT                |clustername    |
|abhishek.verma02@snapdeal.com  |12345          |FASHION JEWELLERY      |PEARL JEWELLERY       |cl_0001        |
