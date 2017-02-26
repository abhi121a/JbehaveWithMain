package org.testing.framework.backend.WebElements.PageElements;

import net.serenitybdd.core.pages.PageObject;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.properties.LoadProjectProperties;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class CheckBox extends PageObject{

    static Logger logger = LoggerFactory.getLogger(CheckBox.class.getName());

    public CheckBox(WebDriver driver) {
        super(driver);
    }


    /**
     * This will tick a checkbox if not already ticked
     *
     * @param field			A keyword location of an element in an xml file as a String
     * @param beanFileName	The name and path of the xml file to look in as a String
     * @param beanPath		The element node type location in the xml file as a String
     * @throws Exception	If the checkbox is not present or can not be clicked
     */
    public void tick_the_checkbox(final String field, final String beanFileName, final String beanPath) throws Exception {
        tick_the_checkbox(true, field, beanFileName, beanPath);
    }

    public void tick_the_checkbox(final String field,String fieldReplacement, final String beanFileName, final String beanPath) throws Exception {
        tick_the_checkbox(true, field,fieldReplacement, beanFileName, beanPath);
    }

    /**
     * This will untick a checkbox if not already ticked
     *
     * @param field			A keyword location of an element in an xml file as a String
     * @param beanFileName	The name and path of the xml file to look in as a String
     * @param beanPath		The element node type location in the xml file as a String
     * @throws Exception	If the checkbox is not present or can not be clicked
     */
    public void untick_the_checkbox(final String field, final String beanFileName, final String beanPath) throws Exception {
        tick_the_checkbox(false, field, beanFileName, beanPath);
    }

    public void untick_the_checkbox(final String field,String fieldReplacementValue, final String beanFileName, final String beanPath) throws Exception {
        tick_the_checkbox(false, field,fieldReplacementValue, beanFileName, beanPath);
    }

    /**
     * This will tick or untick a checkbox
     *
     * @param tick			A simpe boolean that says true to tick or false to untick a checkbox
     * @param field			A keyword location of an element in an xml file as a String
     * @param beanFileName	The name and path of the xml file to look in as a String
     * @param beanPath		The element node type location in the xml file as a String
     * @throws Exception	If the checkbox is not present or can not be clicked
     */
    private void tick_the_checkbox(final boolean tick,final String field,String fieldReplacement, final String beanFileName, final String beanPath) throws Exception {
        // Get the element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field,fieldReplacement,LoadProjectProperties.getStringProperty(LoadProjectProperties.REPLACE_CHARACTER), beanFileName, beanPath, getDriver(), true);

        // If the check box is to be ticked
        if(tick) {
            // Check if the element is selected
            if ( !elementFound.isSelected() ) {
                // If not then tick it
                elementFound.click();
            }
        } else {
            // Check if the element is selected
            if ( elementFound.isSelected() ) {
                // If it is then untick it
                elementFound.click();
            }
        }
    }


    /**
     *
     * @param tick
     * @param field
     * @param beanFileName
     * @param beanPath
     * @throws Exception
     */

    private void tick_the_checkbox(final boolean tick, final String field, final String beanFileName, final String beanPath) throws Exception {
        // Get the element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // If the check box is to be ticked
        if(tick) {
            // Check if the element is selected
            if ( !elementFound.isSelected() ) {
                // If not then tick it
                elementFound.click();
            }
        } else {
            // Check if the element is selected
            if ( elementFound.isSelected() ) {
                // If it is then untick it
                elementFound.click();
            }
        }
    }
}
