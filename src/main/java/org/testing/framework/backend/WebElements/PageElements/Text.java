package org.testing.framework.backend.WebElements.PageElements;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class Text extends PageObject {

    static Logger logger = LoggerFactory.getLogger(Text.class.getName());

    public Text(WebDriver driver) {
        super(driver);
    }

    /**
     * This will get the text inside the element that has been passed in
     *
     * @param field        A keyword location of an element in an xml file as a String
     * @param beanFileName The name and path of the xml file to look in as a String
     * @param beanPath     The element node type location in the xml file as a String
     * @return The text in the element or empty
     * @throws Exception If the element is not present
     */
    public String getTextFromElement(final String field, final String beanFileName, final String beanPath) throws Exception {

        String text = "";

        // Locate the passed element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // If the element has been found
        if (elementFound != null) {

            text = elementFound.getText();

            // areas of text (eg, inside <td> will return
            // element.getAttribute("value") as null
            if (text.isEmpty() && elementFound.getAttribute("value") != null) {
                // get the value from text input elements
                text = elementFound.getAttribute("value");
            }

            if (text.isEmpty()) {
                logger.warn("No text found in element" + field);
            }
        }

        return text;
    }



}