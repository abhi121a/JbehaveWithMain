package org.testing.framework.steps;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.pages.Pages;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
@ContextConfiguration(locations = "/applicationContext.xml")
@Component
public abstract class AuatSteps extends ScenarioSteps {

    private static final long serialVersionUID = -6181218211075957591L;
    private static final String ENV_PROP_LOCATION = "/properties/";

    static Logger logger = LoggerFactory.getLogger(AuatSteps.class.getName());

    Properties prop = new Properties();

    public AuatSteps(Pages pages) {
        super(pages);

        // load properties
        try {
            if(System.getProperty("env")!= null)
            prop.load(AuatSteps.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
            else
                prop.load(AuatSteps.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));
        } catch(IOException e) {
            logger.error("properties can't be loaded", e);
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}

