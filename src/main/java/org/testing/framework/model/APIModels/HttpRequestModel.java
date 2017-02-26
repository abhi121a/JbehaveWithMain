package org.testing.framework.model.APIModels;

import java.io.IOException;
import java.util.Map;

import org.testing.framework.utils.file.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 04-11-2015.
 */
public class HttpRequestModel {

    static Logger logger = LoggerFactory.getLogger(HttpRequestModel.class.getName());

    private String requestMethod;
    private String url;
    private Map<String, String> headers;
    private String data;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * This will get the data from a file location instead of a passed String
     * @param fileName Location of the file as a String
     */
    public void setDataFromFile(String fileName) {

        try {
            // Try getting the file
            this.data = FileHandler.readTextFile(fileName.trim());
        } catch (IOException e) {
            try {
                // Try getting it as a resource
                this.data = FileHandler.readTextFileFromResource(fileName.trim());
            } catch (IOException ex) {
                this.data = "";
                logger.error("Could not read file " + fileName + ", from absolute path or class resource");
                logger.error(e.getMessage());
                logger.error(ex.getMessage());
            }
        }
    }
}
