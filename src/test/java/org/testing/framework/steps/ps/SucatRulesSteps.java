package org.testing.framework.steps.ps;

import net.thucydides.core.pages.Pages;
import org.springframework.stereotype.Component;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;

/**
 * Created by sakshi on 2/12/15.
 */
@Component
public class SucatRulesSteps extends UISteps{

@Resource
private BeanFilePath subcatRuleBeanFilePath;

    public SucatRulesSteps(Pages pages) {super(pages);
    }

    public String getBeanFilePath() {return subcatRuleBeanFilePath.getBeanFilePath();}

    }