package org.testing.framework.steps.ps;

import net.thucydides.core.pages.Pages;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;

/**
 * Created by abhishek.verma02 on 18-11-2015.
 */
public class loginPageSteps extends UISteps {

    @Resource
    private BeanFilePath loginBeanFilePath;

    public loginPageSteps(Pages pages) {
        super(pages);
    }

    public String getBeanFilePath() {
        return loginBeanFilePath.getBeanFilePath();
    }
}

