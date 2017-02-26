package org.testing.framework.steps.ps;

import net.thucydides.core.pages.Pages;
import org.testing.framework.model.UIModels.BeanFilePath;
import org.testing.framework.steps.uiSteps.UISteps;

import javax.annotation.Resource;


/**
 * Created by abhishek.verma02 on 03-12-2015.
 */

public class ManageExceptionSteps extends UISteps {


    @Resource
    private BeanFilePath productManageExceptionPage;


    public ManageExceptionSteps(Pages pages) {
        super(pages);
    }

    public String getBeanFilePath() {return productManageExceptionPage.getBeanFilePath();

    }
}
