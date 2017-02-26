package org.testing.framework.jbehave.ps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.steps.ps.HomepagesSteps;
import org.testing.framework.steps.ps.ManageExceptionSteps;
import org.testing.framework.steps.ps.PriceRulesSteps;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Properties;


/**
 * Created by abhishek.verma02 on 03-12-2015.
 */
public class ExceptionManagePage {

    static Logger logger = LoggerFactory.getLogger(Login_ps.class.getName());


    @Steps
    ManageExceptionSteps ManageExceptionSteps;

    @Steps
    HomepagesSteps HomepageSteps;

    @Steps
    PriceRulesSteps PriceRulesSteps;

    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";


    public ExceptionManagePage() throws IOException {
        prop = new Properties();
        prop.load(ExceptionManagePage.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
       // prop.load(ClusterManagePage.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        if(System.getProperty("env")!= null)
            prop.load(ExceptionManagePage.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(ExceptionManagePage.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }


    @When("In Exception page I type $value in field $field")
    public void typeValueinField(String value, String field) throws Exception {
        logger.info("Enter text" + value);
        ManageExceptionSteps.type_the_value(value, field);
    }
    @When("for a $supc I type $value in field $field")
    public void typeValueinFieldforProduct(String supc, String value, String field) throws Exception {
        logger.info("supc-"+supc+" value-" + value+ "field"+field);
        ManageExceptionSteps.type_the_value_without_clearing(value,field,supc);
    }

    @Then("I clicked button $button")
    public void clickonbutton(String button) {
        try {
            logger.info("I click on button " + button);
            ManageExceptionSteps.click_the_button(button);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Unable to locate the Element");
        }


    }

    @Then ("clicked button $button and check for alert message $msg")
    public void clickOnButtonAndCeckAlert(String button,String message) throws Exception {
        logger.info("click submit button and check for alert");
        ManageExceptionSteps.click_the_link_and_check_alert(button, message);
    }
    @Then ("for a $supc I clicked button $button and check for alert message $msg")
    public void clickOnButtonAndCeckAlertofperticularsupc(String supc,String button,String message) throws Exception {
        logger.info("click submit button and check for alert");
        ManageExceptionSteps.click_the_link_and_check_alert(button,supc, message);
    }


    @Then ("I Verfy the alert message $message")
    public void verifyalertmessage(String message) throws Exception {
    logger.info("Verifying alert message"+message);
        ManageExceptionSteps.assert_javascript_alert_is_present(message,true);

    }

    @Then ("I validated value in $field for $replaceString")
    public void validateaddedexception(String field,String replaceString) throws Exception {
        try {Thread.sleep(5000);
            logger.info("Validate exception created " + field + "in Field " + replaceString);
            ManageExceptionSteps.assert_element_present_from_xml_textArea(field,replaceString);
            }
        catch(Exception e){
        logger.info("No SUPC found");
        }

    }


    @Then("I click homepage $button")
    public void  clickonhomepage(String button) throws Exception {
        logger.info("click submit button" + button );
        // loginPageSteps.click_the_button(button);
        HomepageSteps.click_the_button(button);
        // homepageSteps.assert_that_a_new_window_is_open_with_url();

    }

/*
    @Then("I Validate cluster's $value in field $field")
    public void validatevalueinfield(String values, String replaceString) throws Exception {
        logger.info("Validate Cluster created " + values + "in Field " + replaceString);
        ManageClusterSteps.assert_element_present_from_xml_textArea(values, replaceString);

    }*/


    @Then("I validate that user navigate to $URL")
    public void validatepageurl(String url) throws Exception {
        logger.info("validating page Url"+url);
        logger.info( prop.getProperty(url));
        //after click waiting for page to load a new url
        Thread.sleep(3000);
        ManageExceptionSteps.assert_that_a_new_page_is_open_with_url_contains_keyword(url);
    }


    @Then("Upload file from location $location after click button $choose file")
    public void uploadAFile(String location,String choose) throws Exception {
        logger.info("Upload file from location "+ location);
        logger.info("I click on choose button"+choose);
        ManageExceptionSteps.click_the_button(choose);

        StringSelection ss = new StringSelection("C:\\Users\\abhishek.verma02\\Desktop\\productlist.xlsx");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


       // C:\Users\abhishek.verma02\Desktop/productlist.xlsx
    }
}