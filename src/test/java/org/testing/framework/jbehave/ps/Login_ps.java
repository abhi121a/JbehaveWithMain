package org.testing.framework.jbehave.ps;

//import org.testing.framework.steps.ps.NavigationPanelSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.steps.ps.HomepagesSteps;
import org.testing.framework.steps.dbSteps.MySqlSteps;
import org.testing.framework.steps.ps.loginPageSteps;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by abhishek.verma02 on 18-11-2015.
 */


public class Login_ps {


    static Logger logger = LoggerFactory.getLogger(Login_ps.class.getName());

    // Variables
    @Steps
    loginPageSteps loginPageSteps;
    @Steps
     HomepagesSteps homepageSteps;
    @Steps
    MySqlSteps mySqlSteps;


    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";

    public Login_ps() throws IOException {
        prop = new Properties();
        prop.load(Login_ps.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if(System.getProperty("env")!= null)
            prop.load(Login_ps.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(Login_ps.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }

    @Given("I open panels using $url")
    public void openUrl(String url) throws Exception{
        logger.info("Opening Site " + url);
        loginPageSteps.open_site(url);
    }

    @Given("I open Url uing API $url for $Sucat,$SUPC and $Vendor")
    public void openAPIUrl(String url) throws Exception{
        logger.info("Opening Site " + url);
        loginPageSteps.open_site(url);
    }


    @When("I type $value in field $field")
    public void typeValueinField(String value, String field) throws Exception{
        logger.info("Enter username " + value);
        loginPageSteps.type_the_value(value,field);
    }

    @When("I click on button $button")
    public void clickOnButton(String button) throws Exception{
        logger.info("click submit button" );
        loginPageSteps.click_the_button(button);

    }

    //can we keep dynamic thing here by updating the values

    @Then("user should navigate to $rules_page")
    public void verifyhomepage(String rules_page) throws Exception {
        logger.info("Verify price_rules is present" );
        loginPageSteps.assert_click_element_present(rules_page);
        logger.info("price_rules is present" );
    }

    @Then("I click on $button")
    public void clickonhomepage(String button) throws Exception {
        logger.info("click submit button" + button );
       // loginPageSteps.click_the_button(button);
        homepageSteps.click_the_button(button);
       // homepageSteps.assert_that_a_new_window_is_open_with_url();

        }

    @Then("I validate field $field exist with value $replaceString")
    public void validateField(String field,String replaceString) throws Exception {

    logger.info("ValidateField" + field + replaceString);
    // loginPageSteps.click_the_button(button);
    loginPageSteps.assert_element_present_from_xml_textArea(field,replaceString);

}
    @Then("I open $userInput by click on $button")
    public void clickonhomepage(String userInput, String button) throws Exception {
        logger.info("I open "+userInput+"by clicking on "+button);
        //pre condition userInput should be similar to the text specified in HTML.
        //here we are taking input from user to open a certain sub cat and clicking on the sub cat by replacing the string in xpath.
        homepageSteps.click_the_button(button,userInput);


    }


    @Then("I validate following field is present $field")
    public void checkStringPresetInPage(String field) {
        try {
            loginPageSteps.assert_element_present_from_xml_textArea(field);
        } catch (Exception e) {
            logger.info("text: "+field+ " - Not Displayed");
            e.printStackTrace();
        }




    }

    @Then("validate $Field using My Sql DB results using query $query")
    public void validateMySqlDBResults(String field,String query) throws Exception {
        logger.info("Query is :-" +  mySqlSteps.getMySqlModel(query).getQuery());
        List<HashMap<String,Object>> queryResults = mySqlSteps.executeSelectQuery(mySqlSteps.getMySqlModel(query).getQuery());
        logger.info("Result Row " + queryResults.get(0).toString());
        for(int i=0;i<queryResults.size();i++) {
            HashMap<String, Object> a = queryResults.get(i);
            String cat_id=a.get("category_id").toString();
            String subcat_id=a.get("subcategory_id").toString();
            String cat_name = a.get("category_name").toString();
            String sub_name = a.get("subcategory_name").toString();
            {
                logger.info(cat_id+"  "+subcat_id+"  "+cat_name+" "+sub_name);
            }
        homepageSteps.assert_element_present_from_xml_textArea(field,cat_name);




        }
           /* for (Map.Entry<String, Object> item : a.entrySet()) {
                String key = item.getKey();
                String value = (String) item.getValue();
                logger.info("hash map value "+key+"  "+value);
            }*/
    }




    }
