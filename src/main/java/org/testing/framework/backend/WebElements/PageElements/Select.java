package org.testing.framework.backend.WebElements.PageElements;

import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.testing.framework.properties.LoadProjectProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class Select extends PageObject {

    static Logger logger = LoggerFactory.getLogger(Select.class.getName());

    public Select(WebDriver driver) {
        super(driver);
    }

    public void select_the_element(final String value, final String field,  final String beanFileName, final String beanPath) throws Exception {
        select_the_element(value, field, "", beanFileName, beanPath);
    }

    public void select_the_element(final String value, final String field,  String fieldReplacementValue, final String beanFileName, final String beanPath) throws Exception {
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, fieldReplacementValue, LoadProjectProperties.getStringProperty(LoadProjectProperties.REPLACE_CHARACTER), beanFileName, beanPath, getDriver(), true);
        if (elementFound == null) {
            return;
        }
        List<WebElement> options = elementFound.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getText().contains(value)) {
                logger.info("Option found: "+value);
                option.click();
                return;
            }
        }
        logger.error("Option not found: size: "+options.size()+", value: "+value);
    }
}

