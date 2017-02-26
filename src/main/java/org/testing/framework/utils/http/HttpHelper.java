package org.testing.framework.utils.http;

import org.testing.framework.model.APIModels.HttpRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class HttpHelper {

    static Logger logger = LoggerFactory.getLogger(HttpHelper.class.getName());

    /**
     * Send the http request using the model for the response String
     *
     * @param httpRequest The http request model to use
     * @return The response from the request as a string
     * @throws Exception
     */
    public static String sendHttpRequest(HttpRequestModel httpRequest) throws Exception {
        HttpHandler httpHandler = setUpHttpRequest(httpRequest);
        return httpHandler.sendRequest();
    }

    /**
     * Send the http request using the model for the response code
     *
     * @param httpRequest The http request model to use
     * @return The response from the request as a string
     * @throws Exception
     */
    public static int sendHttpRequestForResponseCode(HttpRequestModel httpRequest) throws Exception {
        HttpHandler httpHandler = setUpHttpRequest(httpRequest);
        httpHandler.sendRequest();
        return httpHandler.getResponseCode();
    }

    /**
     * Set up the http request
     *
     * @param httpRequest The http request model to use
     * @return A httpHandler instance to be used
     * @throws Exception
     */
    private static HttpHandler setUpHttpRequest(HttpRequestModel httpRequest) throws Exception {
        // Setup a new handler and give it the following credentials
        HttpHandler httpHandler = new HttpHandler(httpRequest.getUrl());
        httpHandler.setRequestMethod(httpRequest.getRequestMethod());
        httpHandler.setConnectionHeaders(httpRequest.getHeaders());
        if(httpRequest.getData() != null)
            httpHandler.setData(httpRequest.getData());
        return httpHandler;
    }
}

