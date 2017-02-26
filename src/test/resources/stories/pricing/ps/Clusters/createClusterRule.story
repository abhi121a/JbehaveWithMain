Meta:

Narrative:
As a category user
I want to create a cluster from manage cluster page with specific brand, vendor , supc and with a filter condition.
So that I apply a rule on specific supc vendor combination


Scenario: Login in and selecting a specified subcat from example
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}
Then I validate field EMAIL exist with value <User_Name>

Examples:
|User_Name                 |Password       |CATEGORY_NAME          |SUBCATEGORY_NAME |
|abhishek.verma02@snapdeal.com  |Nov@2015    |Bags & Luggage         |Luggage          |



Scenario: Creating Cluster
When I click on link PRODUCT CONFIG
And I click on link MANAGE CLUSTER
Then I click cluster's CREATE CLUSTER
When I type cluster's <clustername> in the field CLUSTER NAME
!-- no need to change as it already set as All

And I select cluster's dropdown for BRANDTYPE with Selective
!-- Need to handle  the text box
And I select cluster's dropdown for SELLERTYPE with Selective
!-- Need to handle  the text box
!--Then Upload Cluster's file from location <location> after click button CHOOSEFILE file

!-- Filter criteria to be updated




When I type cluster's <DiscountFrom> in the field DISCOUNT FROM
And I type cluster's <DiscountTo> in the field DISCOUNT TO
And I type cluster's <PriceFrom> in the field PRICE FROM
And I type cluster's <Priceto> in the field PRICE TO
And I type cluster's <SellerRatingFrom> in the field RATING FROM
And I type cluster's <SellerRatingTo> in the field RATING TO

!-- clicking create cluster
Then I click cluster's CREATE CLUSTER BUTTON
Then I Validate cluster's CLUSTER TEXT in field <clustername>
Then I click on PRICE RULES
And I click cluster's RULES CLUSTER
And I Validate cluster's CLUSTER RULE CREATED in field <clustername>
!-- values to a cluster rule

!--When I type Cluster rule values <overall_values> in fields CM_GM_CD_MD_NC_NCGM in CLUSTER CONSTRAINS for <clustername>
When I type Cluster rule values <overall_values> in fields CM_GM_CD_MD_NC_NCGM in CLUSTER CONSTRAINS for <clustername>

Then For cluster <clustername> I clicked button UPDATE CLUSTER RULE and check for alert message To process your changes please click PROCESS button at subcategory page


Examples:
|clustername    |location                                           |DiscountFrom|DiscountTo  |PriceFrom|Priceto|SellerRatingFrom | SellerRatingTo    |FullfilmentType    |overall_values  |
|cl_0001        |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |10          |40          |200      |1000   |2                |4                  |Dropship           |7_7_7_7_7_7     |
|cl_0002        |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |40          |50          |200      |1000   |2                |4                  |Dropship           |7_8_8_6_5_7     |
|cl_0003        |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |50          |10          |200      |1000   |2                |4                  |Dropship           |4_5_6_7_8_9     |
|cl_FC_VOI      |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |50          |10          |200      |1000   |2                |4                  |FC_VOI              |-1_7_7_7_-1_7     |
|cl_0005        |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |50          |10          |200      |1000   |2                |4                  |ONESHIP            |7_7_7_7_7_7     |
|cl_All         |C:\Users\abhishek.verma02\Desktop\productlist.xlsx |0n          |0           |         |       |                 |                   |Dropship           |7_7_7_7_7_7     |



Scenario: Click process button for all above clusters
GivenStories: stories/pricing/ps/baseStories/processRules.story


