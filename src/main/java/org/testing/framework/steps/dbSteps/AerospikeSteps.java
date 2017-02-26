package org.testing.framework.steps.dbSteps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.aerospike.core.AerospikeTemplate;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.testing.framework.model.DBModels.AeroSpikeModel;
import org.testing.framework.model.DBModels.MongoDBModel;
import org.testing.framework.steps.AuatSteps;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ravinder Singh on 13-11-2015.
 */
public class AerospikeSteps extends AuatSteps {

    @Autowired
    AerospikeTemplate aeroSpikeTemplate;

    @Resource
    private Map<String, AeroSpikeModel> aeroSpikeMap;

    public AerospikeSteps(Pages pages) {super(pages);
    }
    static Logger logger = LoggerFactory.getLogger(AerospikeSteps.class.getName());

    /**
     * This must return the Database model map of keys to the DB resources etc
     *
     * @return A map of keys and an DB model
     */
    public  Map<String, AeroSpikeModel> getAeroSpikeMap() { return aeroSpikeMap; };

    public AeroSpikeModel getAeroSpikeModel(String key) throws Exception {

        AeroSpikeModel aeroSpikeModel=null;
        // Get the model from the map using the key, throwing the appropriate error if not found
        if (getAeroSpikeMap() != null)
            aeroSpikeModel = getAeroSpikeMap().get(key);
        if (aeroSpikeModel == null) {
            throw new Exception("Specified AeroSpike Model \"" + key + "\" not configured in Application");
        }
        return aeroSpikeModel;
    }

 /*   @Step
    public List<Object> executeFindQuery(String mongoBean) throws Exception {
        Query query = new Query();
        logger.info("Collection Name :- " + getAeroSpikeModel(mongoBean).getCollectionName());
        Set<String> keys = getAeroSpikeModel(mongoBean).getCrtiteria().keySet();
        logger.info("Criterias Specified for find Query:- ");
        //Add all criteria to query
        for(String key: keys) {
            logger.info("Key = " + key + " Value = " + getAeroSpikeModel(mongoBean).getCrtiteria().get(key));
            query.addCriteria(Criteria.where(key).is(getAeroSpikeModel(mongoBean).getCrtiteria().get(key)));
        }
        return aeroSpikeTemplate.findAll();
    }
*/

}
