package org.testing.framework.backend.WebElements.PageElements;

import net.serenitybdd.core.pages.PageObject;
import org.apache.commons.lang.StringUtils;
import org.testing.framework.backend.WebElements.Locator.WebElementLocator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import net.thucydides.core.webdriver.javascript.JavascriptExecutorFacade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class JavaScript extends PageObject {

    static Logger logger = LoggerFactory.getLogger(JavaScript.class.getName());

    public JavaScript(WebDriver driver) {
        super(driver);
    }

    public void removeReadOnlyAttribute(final String field, final String beanFileName, final String beanPath) throws Exception {

        // Locate the passed element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // If the element has been found
        if (elementFound != null) {
            logger.info("document.getElementById('"+ elementFound.getAttribute("id") +"').removeAttribute('readonly',0);");
            JavascriptExecutorFacade javaExecutor = new JavascriptExecutorFacade(getDriver());
            javaExecutor.executeScript("document.getElementById('"+ elementFound.getAttribute("id") +"').removeAttribute('readonly',0);");
        }
    }

    //This will scroll down to bottom of the page
    public void scrollDownToBottomOfPage() throws Exception {
        JavascriptExecutor javaExecuter = (JavascriptExecutor)getDriver();

        javaExecuter.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
    }

    //This will scroll automatically to specified web element
    public void scrollToElement(final String field, final String beanFileName, final String beanPath) throws Exception {
        // Locate the passed element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // If the element has been found
        if (elementFound != null) {
            JavascriptExecutor javaExecutor = (JavascriptExecutor) getDriver();
            javaExecutor.executeScript("arguments[0].scrollIntoView();", elementFound);
        }
    }

    /**
     * Retrieve the value associated to a css key
     * For example:
     *  given: a {color:#777;}
     *  this function will return the string #777 for the cssKey 'color'
     * @param field the web element for which we want to retrieve a CSS information
     * @param cssKey the css key
     * @return the cssValue associated to the cssKey
     * @throws Exception
     */
    public String retrieveCssValue(final String field, String cssKey, final String beanFileName, final String beanPath) throws Exception {
        // Locate the passed element
        WebElementLocator elementLocator = WebElementLocator.getInstance();
        WebElement elementFound = elementLocator.locateElement(field, beanFileName, beanPath, getDriver(), true);

        // If the element has been found
        if (elementFound != null) {
            JavascriptExecutor javaExecutor = (JavascriptExecutor) getDriver();
            // retrieve the css value
            String cssValue = (String)javaExecutor.executeScript("return $(arguments[0]).css('"+cssKey+"');", elementFound);
            //String otherCss = (String)javaExecutor.executeScript("return getComputedStyle(arguments[0]).background-color;", elementFound);
            //logger.info("cssValue: "+cssValue+", otherCss: "+otherCss);
            return cssValue;
        }
        return StringUtils.EMPTY;
    }

}

