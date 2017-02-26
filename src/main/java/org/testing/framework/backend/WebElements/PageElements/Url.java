package org.testing.framework.backend.WebElements.PageElements;

import org.openqa.selenium.WebDriver;


import net.serenitybdd.core.pages.PageObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class Url extends PageObject {

    static Logger logger = LoggerFactory.getLogger(Url.class.getName());

    public Url(WebDriver driver) {
        super(driver);
    }

    public void open_page(String url) throws Exception {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();

        // TODO: Need to figure out a way to throw an error when a page doesn't
        // load. Otherwise
        // the test will just hang until global test timeout.
        // The following do NOT work for some unknown reason:
        // getDriver().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        // getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // getDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.get(url);
    }

    /**
     * This will get the current web pages' url and return it
     *
     * @return The url of the current page as a string
     */
    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public void closeBrowser() {
        getDriver().quit();
    }

    /**
     * This will close the current selected window, if its the only one open then it will close the browser
     */
    public void closeCurrentWindow() {
        getDriver().close();
    }


}
