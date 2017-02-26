package org.testing.framework.steps.dbSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testing.framework.backend.Databases.mySql.MySqlActions;
import org.testing.framework.model.DBModels.MySqlModel;
import org.testing.framework.steps.AuatSteps;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ravinder Singh on 12-11-2015.
 */
@Component
public class MySqlSteps extends AuatSteps {

    public MySqlSteps(Pages pages) {super(pages);
    }

    @Autowired
    private MySqlActions mySqlActionsPublic;

    @Resource
    private Map<String, MySqlModel> mySqlMap;

    static Logger logger = LoggerFactory.getLogger(MySqlSteps.class.getName());

    //@Autowired
    //private MySqlActions mySqlActionsOverSSH;

    /**
     * This must return the Database model map of keys to the DB resources etc
     *
     * @return A map of keys and an DB model
     */
    public Map<String, MySqlModel> getMySqlMap() { return mySqlMap; }

    public MySqlModel getMySqlModel(String key) throws Exception {

       MySqlModel mySqlModel=null;
        // Get the model from the map using the key, throwing the appropriate error if not found
        if (getMySqlMap() != null)
            mySqlModel = getMySqlMap().get(key);
        if (mySqlModel == null) {
            throw new Exception("Specified My Sql Model \"" + key + "\" not configured in Application");
        }
        return mySqlModel;
    }

    @Step
    public List<HashMap<String,Object>> executeSelectQuery(String query) throws SQLException {
        return mySqlActionsPublic.executeSelectQuery(query);
    }

    @Step
    public void executeUpdateQuery(String query) throws SQLException {
        mySqlActionsPublic.executeUpdateQuery(query);
    }

    @Step
    public HashMap<String,Object> executeSelectQueryResultsHashMaps(String query) throws SQLException {
        return mySqlActionsPublic.executeSelectQueryResultingHashMaps(query);
    }
}
