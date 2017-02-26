package org.testing.framework.backend.WebElements.Validations;

import net.serenitybdd.core.pages.PageObject;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class CheckBoxValidations extends PageObject {

    static Logger logger = LoggerFactory.getLogger(CheckBoxValidations.class.getName());

    public CheckBoxValidations(WebDriver driver) {
        super(driver);
    }


    /**
     * This will assert whether a checkbox is checked or not
     *
     * @param field	A keyword location of an element in an xml file as a String
     * @param beanFileName The name and path of the xml file to look in as a String
     * @param beanPath The element node type location in the xml file as a String
     * @throws Exception If the checkbox was meant to be clicked but is not then it will throw error. And vice-versa.
     */
    public void isCheckboxChecked(final boolean checked, final String field, final String beanFileName, final String beanPath) throws Exception {

        // Get the element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // Set up a success boolean
        boolean success = false;

        // If its meant to be unticked or ticked and it is then
        if (( !elementFound.isSelected() && !checked ) || (elementFound.isSelected() && checked)) {
            success = true;
        }

        // Assert whether it was checked or not
        assertTrue(success);
    }
}
