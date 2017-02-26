package org.testing.framework.steps.dbSteps;

import com.mongodb.DBObject;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testing.framework.model.DBModels.MongoDBModel;
import org.testing.framework.steps.AuatSteps;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Ravinder Singh on 13-11-2015.
 */
public class MongoDBSteps  extends AuatSteps {

    public MongoDBSteps(Pages pages) {super(pages);
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private Map<String, MongoDBModel> mongoDBMap;

    static Logger logger = LoggerFactory.getLogger(MongoDBSteps.class.getName());

    /**
     * This must return the Database model map of keys to the DB resources etc
     *
     * @return A map of keys and an DB model
     */
    public  Map<String, MongoDBModel> getMongoDBMap() { return mongoDBMap; };

    public MongoDBModel getMongoDBModel(String key) throws Exception {

        MongoDBModel mongoDBModel=null;
        // Get the model from the map using the key, throwing the appropriate error if not found
        if (getMongoDBMap() != null)
            mongoDBModel = getMongoDBMap().get(key);
        if (mongoDBModel == null) {
            throw new Exception("Specified My MongoDB Model \"" + key + "\" not configured in Application");
        }
        return mongoDBModel;
    }

    /**
     *
     * @param mongoBean Bean ID
     * @return List of object datatype
     * @throws Exception
     */
    @Step
    public List<DBObject> executeFindQuery(String mongoBean) throws Exception {
        Query query = new Query();
        logger.info("Collection Name :- " + getMongoDBModel(mongoBean).getCollectionName());
        Set<String> keys = getMongoDBModel(mongoBean).getCrtiteria().keySet();
        logger.info("Criterias Specified for find Query:- ");
        //Add all criteria to query
        for(String key: keys) {
            logger.info("Key = " + key + " Value = " + getMongoDBModel(mongoBean).getCrtiteria().get(key));
            query.addCriteria(Criteria.where(key).is(getMongoDBModel(mongoBean).getCrtiteria().get(key)));
        }
        List<DBObject> result= mongoTemplate.find(query, DBObject.class, getMongoDBModel(mongoBean).getCollectionName());

        return result;
    }
}
