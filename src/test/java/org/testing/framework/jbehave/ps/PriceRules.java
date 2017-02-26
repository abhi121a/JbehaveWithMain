package org.testing.framework.jbehave.ps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.steps.ps.PriceRulesSteps;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by abhishek.verma02 on 07-12-2015.
 */
public class PriceRules {

    static Logger logger = LoggerFactory.getLogger(Login_ps.class.getName());


    @Steps
    PriceRulesSteps PriceRulesSteps;


    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";


    public PriceRules() throws IOException {
        prop = new Properties();
        prop.load(PriceRules.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if(System.getProperty("env")!= null)
            prop.load(PriceRules.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(PriceRules.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }



    @Then("I Click PriceRules button $Button")
    public void  clickonhomepage(String button) throws Exception {
        logger.info("click submit button" + button );
        // loginPageSteps.click_the_button(button);
        PriceRulesSteps.click_the_button(button);
        // homepageSteps.assert_that_a_new_window_is_open_with_url();

    }





}
