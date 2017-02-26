package org.testing.framework.backend.WebElements.Validations;

import net.serenitybdd.core.pages.PageObject;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class UrlValidations extends PageObject {

    static Logger logger = LoggerFactory.getLogger(UrlValidations.class.getName());

    public UrlValidations(WebDriver driver) {
        super(driver);
    }

    /**
     * This will assert whether the current page contains the url address being passed
     * with a given url in the xml file from the field location
     *
     * @param field			A keyword location of an element in an xml file as a String
     * @param beanFileName	The name and path of the xml file to look in as a String
     * @param beanPath		The element node type location in the xml file as a String
     * @throws Exception	If the current windows url is not the one thats been passed
     */
    public void isCurrentWindowUrlCorrect(final String field, final String beanFileName, final String beanPath) throws Exception {

        // Find the url from the field in the xml file

        // Create an element locator
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        // Get the value of the field in the xml file and put it into the string url
        String url = elementLocator.getElementPath(field, beanFileName, beanPath);

        // Assert true if the current url contains the url passed else false
        boolean contains = getDriver().getCurrentUrl().contains(url);
        if(!contains) {
            logger.error(getDriver().getCurrentUrl()+" doesn't contain "+url);
        }
        assertTrue(contains);
    }

    public void isCurrentWindowUrlCorrect(final String valueToSearch) throws Exception {
        logger.info("Current Url for Page is -"+getDriver().getCurrentUrl().toString());
        boolean contains = getDriver().getCurrentUrl().contains(valueToSearch);
        if(!contains) {
            logger.error(getDriver().getCurrentUrl()+" doesn't contain "+valueToSearch);
        }
        assertTrue(contains);
    }

    /**
     * This will check to see if there is a new window open
     * It achieves this by looking at how many windows it can find, if more than one then asserts true
     * This will also make the window the come to the fore front
     *
     * @throws Exception	If a new windows window is not open or can be switched to
     */
    public void isANewWindowOpen() throws Exception {

        // Set a boolean value
        boolean newWindow = false;

        // Get the driver
        WebDriver driver = getDriver();

        // Get all the available windows open as a list
        ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());
        // If there is more than 1 window open
        if (availableWindows.size() > 1) {
            // Switch to the last one
            driver.switchTo().window(availableWindows.get(availableWindows.size()-1));
            newWindow = true;
        }

        // Assert whether the window was open or not
        assertTrue(newWindow);
    }

    /**
     * This will check to see if there is a new window open with a given url in the xml file from the field location
     * It achieves this by looking at how many windows it can find, if more than one then asserts true
     * This will also make the window the come to the fore front
     *
     * @param field			A keyword location of an element in an xml file as a String
     * @param beanFileName	The name and path of the xml file to look in as a String
     * @param beanPath		The element node type location in the xml file as a String
     * @throws Exception	If a new window is not present with the passed url
     */
    public void isANewWindowOpenWithUrl(final String field, final String beanFileName, final String beanPath) throws Exception {

        // Set a boolean value
        boolean newWindow = false;

        // Find the url from the field in the xml file

        // Create an element locator
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        // Get the value of the field in the xml file and put it into the string url
        String url = elementLocator.getElementPath(field, beanFileName, beanPath);

        // If there is a url to find then search for it
        if(!url.isEmpty()) {
            // Get the driver
            WebDriver driver = getDriver();

            // Get all the available windows open as a list
            ArrayList<String> availableWindows = new ArrayList<String>(driver.getWindowHandles());

            // If there is at least 1 window open
            if (availableWindows.size() >= 1) {
                // Check each window open
                for(String window : availableWindows) {
                    // Switch to the window
                    driver.switchTo().window(window);
                    // Get the current url of the window
                    String windowUrl = driver.getCurrentUrl();

                    // If the window is the one with the passed url
                    if(windowUrl.contains(url)) {
                        // Url is found so set true and exit out of the loop
                        logger.info("Found: " + url);
                        newWindow = true;
                        break;
                    }
                }
            }
        }

        // Assert whether the window was open or not
        assertTrue(newWindow);
    }

}
