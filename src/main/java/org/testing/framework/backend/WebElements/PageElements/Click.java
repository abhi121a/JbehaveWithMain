package org.testing.framework.backend.WebElements.PageElements;

import net.serenitybdd.core.pages.PageObject;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.testing.framework.properties.LoadProjectProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testing.framework.steps.uiSteps.UISteps;

/**
 * Created by Ravinder Singh on 02-11-2015.
 */
public class Click extends PageObject {

    static Logger logger = LoggerFactory.getLogger(Click.class.getName());

    public Click(WebDriver driver) {
        super(driver);
    }


    /**
     * This Method can click on multiple elements separated by a delimiter '_'
     *
     * @param keyword                 A keyword location of an element in an xml file as a String
     * @param beanFileName             The name and path of the xml file to look in as a String
     * @param beanPath                 The element node type location in the xml file as a String
     * @param raiseErrorIfUnavailable Raise an error if unavailable
     * @throws Exception If the element is not present
     */
    public void click_the_element(final String keyword, final String beanFileName, final String beanPath, final boolean raiseErrorIfUnavailable)
            throws Exception {

        WebElementLocator elementLocator = WebElementLocator.getInstance();

        String[] keywordList = keyword.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));

        for (String singleKey : keywordList) {

            WebElement elementFound = elementLocator.locateElement(singleKey, beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);

            if (elementFound != null) {
                elementFound.click();
            }
        }
    }

    /**
     *  To click on all web elements located by one or multiple keywords (delimited with _)
     *  Wait ajax busy identifier after each click.
     */
    public void click_multiple_elements_if_exists_and_wait_for_ajax(UISteps steps, final String keyword, final String beanFileName, final String beanPath, final boolean raiseErrorIfUnavailable)
            throws Exception {
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        String[] keywordList = keyword.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));
        for (String singleKey : keywordList) {
            int nbElements = elementLocator.getElementCount(singleKey, beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);
            for (int i=0;i<nbElements;i++) {
                WebElement elementFound = elementLocator.locateElement(singleKey, beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);
                if (elementFound != null) {
                    elementFound.click();
                    steps.waitForAjaxBusyIdentifier();
                }
            }
        }
    }

    /**
     * This Method can click on multiple elements separated by a delimiter '_'.
     * <p>
     * Has the ability to substitute a value in the xml file with passed replace variables.
     *
     * @param keyword A keyword location of an element in an xml file as a String
     * @param replaceString	A string to insert into the value found from the keyword, can be multiples split by a delimiter, replace String is ##
     * @param beanFileName The name and path of the xml file to look in as a String
     * @param beanPath The element node type location in the xml file as a String
     * @param raiseErrorIfUnavailable Raise an error if unavailable
     * @throws Exception If the element is not present
     */
    public void click_the_element(final String keyword, final String replaceString, final String beanFileName, final String beanPath, final boolean raiseErrorIfUnavailable)
            throws Exception {

        String[] keywordList = keyword.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));
        String[] replaceList = replaceString.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));

        // If the lists are the same length then do the checking, else throw an error
        if(keywordList.length == replaceList.length) {

            // Set up the variables for the loop
            int count = keywordList.length;
            WebElementLocator elementLocator = WebElementLocator.getInstance();

            // For each item in the lists
            for(int i=0; i<count; i++) {

                WebElement elementFound = elementLocator.locateElement(keywordList[i], replaceList[i],
                        LoadProjectProperties.getStringProperty(LoadProjectProperties.REPLACE_CHARACTER), beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);

                if (elementFound != null) {
                    elementFound.click();
                }
            }
        } else {
            throw new Exception("Keyword list and replace list count does not match");
        }
    }

    /**
     * This Method can click on multiple elements separated by a delimiter '_'.
     * <p>
     * Has the ability to substitute multiple values in the xml file with passed replace variables.
     *
     * @param keyword A keyword location of an element in an xml file as a String
     * @param replaceString	A string to insert into the value found from the keyword, can be multiples split by a delimiter, replace String is ##
     * @param replaceDelimeter	delimeter to be replaced by the given string
     * @param beanFileName The name and path of the xml file to look in as a String
     * @param beanPath The element node type location in the xml file as a String
     * @param raiseErrorIfUnavailable Raise an error if unavailable
     * @throws Exception If the element is not present
     */
    public void click_the_element(final String keyword, final String replaceString, final String replaceDelimeter, final String beanFileName, final String beanPath, final boolean raiseErrorIfUnavailable)
            throws Exception {

        String[] keywordList = keyword.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));
        String[] replaceList = replaceString.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));

        // If the lists are the same length then do the checking, else throw an error
        if(keywordList.length == replaceList.length) {

            // Set up the variables for the loop
            int count = keywordList.length;
            WebElementLocator elementLocator = WebElementLocator.getInstance();

            // For each item in the lists
            for(int i=0; i<count; i++) {

                WebElement elementFound = elementLocator.locateElement(keywordList[i], replaceList[i],
                        replaceDelimeter, beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);

                if (elementFound != null) {
                    elementFound.click();
                }
            }
        } else {
            throw new Exception("Keyword list and replace list count does not match");
        }
    }

    public Boolean wait_the_element(final String keyword, final String beanFileName, final String beanPath, final boolean raiseErrorIfUnavailable)
            throws Exception {
        Boolean flag =false;

        WebElementLocator elementLocator = WebElementLocator.getInstance();

        String[] keywordList = keyword.split(LoadProjectProperties.getStringProperty(LoadProjectProperties.ASSERT_TEXT_DELIMITER));

        for (String singleKey : keywordList) {

            WebElement elementFound = elementLocator.locateElement(singleKey, beanFileName, beanPath, getDriver(), raiseErrorIfUnavailable);

            if (elementFound != null) {
                flag = true;
            }
        }
        return flag;
    }
}
