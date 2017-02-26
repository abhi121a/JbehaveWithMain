package org.testing.framework.jbehave.assortment;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.jbehave.ps.Login_ps;
import org.testing.framework.steps.dbSteps.MySqlSteps;
import org.testing.framework.steps.asssortment.nullSearchReportSteps;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by sakshi on 17/12/15.
 */
public class NullSearchReport  {

    static Logger logger = LoggerFactory.getLogger(Login_ps.class.getName());


    public NullSearchReport(WebDriver driver) throws IOException
    {
        this();
        //pageObject = new PageObject().setDriver(driver);
    }

    @Steps
    nullSearchReportSteps nullSearchReportSteps;
    @Steps
    MySqlSteps mySqlSteps;

    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";

    WebDriver driver;
    public NullSearchReport() throws IOException {
        prop = new Properties();
        prop.load(Login_ps.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if(System.getProperty("env")!= null)
            prop.load(Login_ps.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(Login_ps.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));
    }

    @Given("I open assortment using $url")
    public void openUrl(String url) throws Exception{
        logger.info("Opening Site " + url);
       nullSearchReportSteps.open_page_from_url(url);
    }

    @When("I enter $value in field $field")
    public void enterValueinField(String value, String field) throws Exception{
        nullSearchReportSteps.type_the_value(value,field);

    }

    @Then("I validate if $page page is open")
    public void correctPageisOpen(String pageKeyword) throws  Exception{
        nullSearchReportSteps.assert_that_a_new_page_is_open_with_url_contains_keyword(pageKeyword);
    }

    @When("I get table size from table $tablename")
    public void getTableData(String tablename) throws  Exception{
        Integer size=nullSearchReportSteps.getTableSize(tablename,"//tr");
        logger.info("size of the table"+size);
    }

    @When("I click on $button button on $Page")
    public void clickOnButton(String button) throws Exception{
        logger.info("click"+button+"button" );
       nullSearchReportSteps.click_the_button(button);
    }

    @When("I switch to frame $frame click on $button button and select $date on $page")
    public void clickOnCalender(String button) throws Exception{
        nullSearchReportSteps.switch_to_iframe("");
        logger.info("click"+button+"button" );
        nullSearchReportSteps.click_the_button(button);
    }

    @When("I get size from table $tablename")
    public Integer getTableSize(String tablename) throws  Exception{
        Integer size=nullSearchReportSteps.getTableSize(tablename,"./tr");
        logger.info("size of the table"+size);
        return  size;
    }


}
