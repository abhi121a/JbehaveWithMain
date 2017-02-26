Meta:

Narrative:
As a user
I want to create a subcat overall rule
and validate the status in ps_group and ps_user_input table
which should be -1.



Scenario:
GivenStories: stories/pricing/ps/baseStories/loginPage.story#{0},stories/pricing/ps/baseStories/selectSubcatOpens.story#{0}


When I click on rule OVERALL
When I type values <overall_values> in fields CM_GM_CD_MD_NC_NCGM in OVERALL_INPUTS
Then I click the UPDATE button and check for alert message To process your changes please click PROCESS button at subcategory page
Then Check for Message UPDATION_CONFIRMATION_MSG
Then I click the PROCESS button and check for alert message Please click on Apply Cashback BUTTON in view Summary Page to apply changes.
Then Check for Message PROCESS_CONFIRMATION_MSG
When I click on the button VIEW SUMMARY BUTTON
Then I validate My Sql DB results for subcatrule using query SelectAllFromPSGroupBeforeCashback with <subcatID>
Then I validate My Sql DB results for viewSummary using query SelectAllFromPSUserInputBeforeCashback



Examples:
|User_Name                   |Password  |overall_values  |subcatID|CATEGORY_NAME       |SUBCATEGORY_NAME   |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |27      |Mobiles & Tablets      |GSM Handsets            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |19      |Mobiles & Tablets      |Mobile Accessories            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |133      |Mobiles & Tablets      |Tablets            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |175      |Mobiles & Tablets      |Mobile Phones        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |228      |Mobiles & Tablets      |Memory Cards            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |336      |Mobiles & Tablets      |Tablet Accessories           |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |675      |Mobiles & Tablets      |Bluetooth Devices           |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |676      |Mobiles & Tablets      |Cases & Covers            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |677      |Mobiles & Tablets      |Screen Guards            |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |678      |Mobiles & Tablets      |Batteries         |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |679     |Mobiles & Tablets      |Power Banks         |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |726      |Mobiles & Tablets      |Mobile Dualsim         |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |38      |Mobiles & Tablets      |Luggage       |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |402     |Mobiles & Tablets      |Backpacks       |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |434      |Mobiles & Tablets      |Laptop Bags         |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |435      |Mobiles & Tablets      |Travel Accessories        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |436      |Mobiles & Tablets      |School Bags       |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |438      |Mobiles & Tablets      |Travel Bags      |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |572      |Mobiles & Tablets      |Utility Bags        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |573      |Mobiles & Tablets      |Clutches        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |43      |Mobiles & Tablets      |Jewellery        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |48      |Mobiles & Tablets      |Anklets, Toe-rings & More        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |49     |Mobiles & Tablets      |Mens Jewellery        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |342      |Mobiles & Tablets      |Rings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |341      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |
|abhishek.verma02@snapdeal.com|Nov@2015 |7_7_7_7_7_7     |16      |Mobiles & Tablets      |Earrings        |


