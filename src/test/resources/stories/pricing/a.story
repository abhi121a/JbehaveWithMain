Meta:

Narrative:
As a user
I want to create a subcat overall rule
and validate the status in ps_group and ps_user_input table
which should be -1.



Scenario:
Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: login
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0}
Then I validate field EMAIL exist with value <User_Name>
Examples:
|User_Name                 |Password       |
|abhishek.verma02@snapdeal.com  |Nov@2015   |

Scenario: click subcat
Then I open <CATEGORY_NAME> by click on CATEGORY
Then I open <SUBCATEGORY_NAME> by click on SUBCATEGORY
And I click homepage PRICE RULES
Then I validate field EXCEPTIONS_CLUSTERS_BRANDS_SUBCATEGORY if present
When I click on rule OVERALL
When I type values <overall_values> in fields CM_GM_CD_MD_NC_NCGM in OVERALL_INPUTS
Then I click the UPDATE button and check for alert message To process your changes please click PROCESS button at subcategory page
Then Check for Message UPDATION_CONFIRMATION_MSG
Then I click the PROCESS button and check for alert message Please click on Apply Cashback BUTTON in view Summary Page to apply changes.
Then Check for Message PROCESS_CONFIRMATION_MSG
When I click on the button VIEW SUMMARY BUTTON

When I tick HeadCheckbox
When I click on the button APPLYALL

When I click on link PRODUCT CONFIG
When I click on link ManageSubcat
Then I click on Update



Then I open <CATEGORY_NAME> by click on CATEGORY



Examples:
|overall_values  |CATEGORY_NAME       |SUBCATEGORY_NAME   |
|7_7_7_7_7_1    |Mobiles & Tablets      |GSM Handsets            |
|7_7_7_7_7_2     |Mobiles & Tablets      |Mobile Accessories            |
|7_7_7_7_7_4     |Mobiles & Tablets      |Tablets            |
|7_7_7_7_7_3     |Mobiles & Tablets      |Mobile Phones        |
|7_7_7_7_7_2     |Mobiles & Tablets      |Memory Cards            |
|7_7_7_7_7_4     |Mobiles & Tablets      |Tablet Accessories           |
|7_7_7_7_7_2     |Mobiles & Tablets      |Bluetooth Devices           |
|7_7_7_7_7_1     |Mobiles & Tablets      |Cases & Covers            |
|7_7_7_7_7_8     |Mobiles & Tablets      |Screen Guards            |
|7_7_7_7_7_1     |Mobiles & Tablets      |Batteries         |
|7_7_7_7_7_4     |Mobiles & Tablets      |Power Banks         |
|7_7_7_7_7_1     |Mobiles & Tablets      |Mobile Dualsim         |
|7_7_7_7_7_4     |Bags & Luggage|Luggage       |
|7_7_7_7_7_7     |Bags & Luggage|Backpacks       |
|7_7_7_7_7_7     |Bags & Luggage|Laptop Bags         |
|7_7_7_7_7_7     |Bags & Luggage|Travel Accessories        |
|7_7_7_7_7_7     |Bags & Luggage|School Bags       |
|7_7_7_7_7_7     |Bags & Luggage|Travel Bags      |
|7_7_7_7_7_7     |Bags & Luggage|Utility Bags        |
|7_7_7_7_7_7     |Bags & Luggage|Clutches        |
|7_7_7_7_7_7     |Jewellery|Pearl Jewellery|
|7_7_7_7_7_7     |Jewellery|Anklets, Toe-rings & More|
|7_7_7_7_7_7     |Jewellery|Mens Jewellery|
|7_7_7_7_7_7     |Jewellery|Earrings|
|7_7_7_7_7_7     |Jewellery|Rings|
|7_7_7_7_7_7     |Jewellery|Necklaces & Sets|
|7_7_7_7_7_7     |Jewellery|Mangalsutra|
|7_7_7_7_7_7     |Jewellery|Bangles & Bracelets|
|7_7_7_7_7_7     |Jewellery|Nosepins|
|7_7_7_7_7_7     |Jewellery|Chains|
|7_7_7_7_7_7     |Jewellery|Gold Coins & Bars|
|7_7_7_7_7_7     |Jewellery|Loose Solitaires & Gems|
|7_7_7_7_7_7     |Jewellery|Idols & Articles|
|7_7_7_7_7_7     |Jewellery|Gifting Ideas & Hampers|
|7_7_7_7_7_7     |Jewellery|Jewellery Boxes & Cleaning Kits|
|7_7_7_7_7_7     |Jewellery|Kids Jewellery|
|7_7_7_7_7_7     |Jewellery|Body Studs|
|7_7_7_7_7_7     |Jewellery|Wedding Accessories|
|7_7_7_7_7_7     |Appliances|Air Conditioner|
|7_7_7_7_7_7     |Appliances|Food Processors|
|7_7_7_7_7_7     |Appliances|Gas Stoves & Hobs|
|7_7_7_7_7_7     |Appliances|Induction Cookers|
|7_7_7_7_7_7     |Appliances|Microwave Ovens & OTGs|
|7_7_7_7_7_7     |Appliances|Toasters & Sandwich maker|
|7_7_7_7_7_7     |Appliances|Roti maker & Snack maker|
|7_7_7_7_7_7     |Appliances|Imported Appliances|
|7_7_7_7_7_7     |Appliances|Outdoor & Utility Appliances|
|7_7_7_7_7_7     |Appliances|testing|
|7_7_7_7_7_7     |Appliances|xxx_yyy_zzz|
|7_7_7_7_7_8|Precious Jewellery|Pendants|
|7_7_7_7_7_9|Precious Jewellery|Coins & Bars|
|7_7_7_7_7_10|Precious Jewellery|Nosepins & Noserings|
|7_7_7_7_7_11|Precious Jewellery|Loose Diamonds|
|7_7_7_7_7_12|Precious Jewellery|Precious Gifts & Articles|
|7_7_7_7_7_13|Precious Jewellery|Anklets & Toe-rings|
|7_7_7_7_7_14|Cameras & Accessories|Memory Cards|






