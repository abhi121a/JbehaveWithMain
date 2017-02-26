package org.testing.framework.jbehave;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.ThucydidesSystemProperty;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.steps.ParameterControls;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class AcceptanceTestSuite extends SerenityStories {
    static Logger logger = LoggerFactory.getLogger(AcceptanceTestSuite.class.getName());


    private String storyPath = "";

    public AcceptanceTestSuite() throws Exception {


        // configuration
        configuration().useParameterControls(new ParameterControls("<", ">", true));

        // Get the story path to run
        storyPath = System.getProperty("featureStoryPath","");
        storyPath="a";
        // If it's not set then exit
        if (storyPath.isEmpty() || storyPath == null) {
            throw new Exception("No story path set, exiting... Please set story path by maven property featureStoryPath");
        }

        // Else log path and run stories
        //logger.info("Running story(ies) in: ",storyPath);
        List<String> findStoryPaths = storyPaths();
        if(!findStoryPaths.isEmpty()) {
            logger.info("Running story(ies) in: " + findStoryPaths.get(0));
            findStoriesCalled(findStoryPaths.toString());
        } else {
            throw new Exception("No story(ies) found in path, exiting... Please check it exists in the project");
        }
    }

    @Override
    public List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocationFromClass(this.getClass()).getFile(), asList("**/" + storyPath + ".story"), null);
    }


}
