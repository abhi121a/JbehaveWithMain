package org.testing.framework.steps.ps;

import net.thucydides.core.pages.Pages;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;

/**
 * Created by abhishek.verma02 on 19-11-2015.
 */
public class HomepagesSteps extends UISteps {

    @Resource
    private BeanFilePath homepageBeanFilePath;

    public HomepagesSteps(Pages pages) {
        super(pages);
    }

    public String getBeanFilePath() {
        return homepageBeanFilePath.getBeanFilePath();
    }
}


