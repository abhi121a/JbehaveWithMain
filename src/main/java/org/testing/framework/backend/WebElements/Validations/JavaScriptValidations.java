package org.testing.framework.backend.WebElements.Validations;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class JavaScriptValidations extends PageObject {

    static Logger logger = LoggerFactory.getLogger(JavaScriptValidations.class.getName());

    public JavaScriptValidations(WebDriver driver) {
        super(driver);
    }


    /**
     * Checks to see if there is an javascript alert present
     *
     * @throws Exception If no javascript alert is present
     */
    public Boolean isJavascriptAlert() {
        return isJavascriptAlert("", false);
    }

    public Boolean isJavascriptAlert(String errorMsg) {
        return isJavascriptAlert(errorMsg, false);
    }

    /**
     * Checks to see if there is an javascript alert present with a given error message text
     *
     * @param errorMsg The message to check for as a String
     * @throws Exception If no javascript alert is present or the message does not match
     */
    public Boolean isJavascriptAlert(String errorMsg, boolean ignoreError) {
        // Set up a boolean value to follow the alert and set it to not found
        boolean alertFound = false;

        // Try and get the alert
        try {
            // Get the alert
            Alert alert = getDriver().switchTo().alert();

            // If the error message is not empty then test it
            if(!errorMsg.isEmpty()) {
                // If the alert text is as expected
                logger.info(alert.getText());
                if(alert.getText().trim().equalsIgnoreCase(errorMsg.trim())) {
                    // Set to alert found to true
                    alertFound = true;
                }
            }
            // Its not for testing the message, just accept the alert is present
            else {
                // Set to alert found to true
                alertFound = true;
            }
        }
        // If its not present then say so
        catch(NoAlertPresentException Ex) {
            logger.info("No javascript alert found");
        }
        if(ignoreError) {
            return alertFound;
        }

        return alertFound;
    }

    /**
     * Checks to see if there is an javascript alert present and accept it
     *
     * @return  Alert Message
     */
    public String assert_and_accept_JavascriptAlert() {
        Boolean isAlertPresent = isJavascriptAlert("", false);
        assertTrue("Javascript alert is not present",isAlertPresent);
        Alert alert = getDriver().switchTo().alert();// Accept it so that the web driver can move on
        String strAlertText = alert.getText();
        alert.accept();
        logger.info("Javascript alert has been Accepted: "+ strAlertText);
        return strAlertText;
    }

    /**
     * Checks to see if there is an javascript alert present and accept it
     *
     * @param errorMsg The message to check for as a String
     * @return Alert Message
     */
    public String assert_and_accept_JavascriptAlert(String errorMsg) {
        Boolean isAlertPresent = isJavascriptAlert(errorMsg, false);
        assertTrue("Javascript alert is not present",isAlertPresent);
        Alert alert = getDriver().switchTo().alert();// Accept it so that the web driver can move on
        String strAlertText = alert.getText();
        alert.accept();
        logger.info("Javascript alert has been Accepted: "+ strAlertText);
        return strAlertText;
    }

    /**
     * Checks to see if there is an javascript alert present and dismiss it
     *
     * @return  Alert Message
     */
    public String assert_and_dismiss_JavascriptAlert() {
        Boolean isAlertPresent = isJavascriptAlert("", false);
        assertTrue("Javascript alert is not present",isAlertPresent);
        Alert alert = getDriver().switchTo().alert();// Accept it so that the web driver can move on
        String strAlertText = alert.getText();
        alert.dismiss();
        logger.info("Javascript alert has been Dismissed: "+ strAlertText);
        return strAlertText;
    }

    /**
     * Checks to see if there is an javascript alert present and dismiss it
     *
     * @param errorMsg The message to check for as a String
     * @return Alert Message
     */
    public String assert_and_dismiss_JavascriptAlert(String errorMsg) {
        Boolean isAlertPresent = isJavascriptAlert(errorMsg, false);
        assertTrue("Javascript alert is not present",isAlertPresent);
        Alert alert = getDriver().switchTo().alert();// Accept it so that the web driver can move on
        String strAlertText = alert.getText();
        alert.dismiss();
        logger.info("Javascript alert has been Dismissed: "+ strAlertText);
        return strAlertText;
    }
}
