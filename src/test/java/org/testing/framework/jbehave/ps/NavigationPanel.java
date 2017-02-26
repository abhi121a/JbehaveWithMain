package org.testing.framework.jbehave.ps;

import net.thucydides.core.annotations.Steps;
import org.testing.framework.steps.ps.NavigationPanelSteps;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.steps.dbSteps.MongoDBSteps;
import org.testing.framework.steps.dbSteps.MySqlSteps;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class NavigationPanel {

    static Logger logger = LoggerFactory.getLogger(NavigationPanel.class.getName());

    // Variables
    @Steps
    NavigationPanelSteps navigationPanelSteps;

    @Steps
    MySqlSteps mySqlSteps;

    @Steps
    MongoDBSteps mongoDBSteps;

    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";

    public NavigationPanel() throws IOException {
        prop = new Properties();
        prop.load(NavigationPanel.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        prop.load(NavigationPanel.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
    }
/*

    @Given("I open panels using $url")
    public void openUrl(String url) throws Exception{
        logger.info("Opening Site " + url);
        navigationPanelSteps.open_site(url);
    }

    @When("I type $value in field $field")
    public void typeValueinField(String value, String field) throws Exception{
        navigationPanelSteps.type_the_value(value,field);
    }

    @When("I click on button $button")
    public void clickOnButton(String button) throws Exception{
        navigationPanelSteps.click_the_button(button);
    }

    @Then("I validate My Sql DB results using query $query")
    public void validateMySqlDBResults(String query) throws Exception {
        logger.info("Query is :-" +  mySqlSteps.getMySqlModel(query).getQuery());
        List<HashMap<String,Object>> queryResults = mySqlSteps.executeSelectQuery(mySqlSteps.getMySqlModel(query).getQuery());
        logger.info("Result Row " + queryResults.get(0).toString());

    }

    @Then("I validate MongoDB results using query $query")
    public void validateMongoDBResults(String query) throws Exception {
       logger.info("Results from Mongo " + mongoDBSteps.executeFindQuery(query));
}
*/

}


