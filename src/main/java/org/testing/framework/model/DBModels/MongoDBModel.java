package org.testing.framework.model.DBModels;

import java.util.HashMap;

/**
 * Created by ravinder.singh04 on 13-11-2015.
 */
public class MongoDBModel {

    private String collectionName;
    private HashMap<String, String> crtiteria;

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public HashMap<String, String> getCrtiteria() {
        return crtiteria;
    }

    public void setCrtiteria(HashMap<String, String> crtiteria) {
        this.crtiteria = crtiteria;
    }

}
