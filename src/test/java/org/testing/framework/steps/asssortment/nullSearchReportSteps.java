package org.testing.framework.steps.asssortment;

import net.thucydides.core.pages.Pages;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;

/**
 * Created by sakshi on 17/12/15.
 */
public class nullSearchReportSteps extends UISteps{

    @Resource
    private BeanFilePath nullSearchReportSteps;

    public nullSearchReportSteps(Pages pages) {
        super(pages);
    }

    public String getBeanFilePath() {
        return nullSearchReportSteps.getBeanFilePath();
    }
}
