package org.testing.framework.steps.ps;

import net.thucydides.core.pages.Pages;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.springframework.stereotype.Component;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
@Component
public class NavigationPanelSteps extends UISteps {

    @Resource
    private BeanFilePath navigationPanelBeanFilePath;

    public NavigationPanelSteps(Pages pages) {
        super(pages);
    }

    public String getBeanFilePath() {
        return navigationPanelBeanFilePath.getBeanFilePath();
    }
}
