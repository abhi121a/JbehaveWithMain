package org.testing.framework.utils.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class HttpHandler {

    static Logger logger = LoggerFactory.getLogger(HttpHandler.class.getName());

    private URL url;
    private HttpURLConnection con;
    private String data = "";

    private final static String NEW_LINE = "\n";

    private static final String POST_REQUEST = "POST";
    private static final String GET_REQUEST = "GET";

    /**
     * Set up a HttpHandler with a url as a String
     *
     * @param urlString A url as a string to open a connection to
     * @throws Exception
     */
    public HttpHandler(String urlString) throws Exception {
        this.url = new URL(urlString);
        this.openConnection();
    }

    /**
     * Set up a HttpHandler with a URL
     *
     * @param url A URL to open a connection to
     * @throws Exception
     */
    public HttpHandler(URL url) throws Exception {
        this.url = url;
        this.openConnection();
    }

    // Setting up connection

    /**
     * Open a connection to the set url
     *
     * @throws Exception For no url set and IOException on connection
     */
    private void openConnection() throws Exception {
        if(!this.url.toString().isEmpty()) {
            con = (HttpURLConnection) this.url.openConnection();
            // Set the connection to allow reading and writing
            con.setDoInput(true);
            con.setDoOutput(true);
        } else {
            throw new Exception("No URL set, set one before opening a connection");
        }
    }

    /**
     * Returns the HttpURLConnection so that further work can be done directly here
     *
     * @return A HttpUrlConnection
     */
    public HttpURLConnection getConnection() {
        return con;
    }

    public URL getUrl() {
        return url;
    }

    // Before request sent

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * Set a post request with the supplied data
     *
     * @param data The data to post as a String
     * @throws ProtocolException
     */
    public void setPost(String data) throws ProtocolException  {
        this.setRequestMethod(POST_REQUEST);
        this.data = data;
    }

    /**
     * Set a get request
     *
     * @throws ProtocolException
     */
    public void setGet() throws ProtocolException {
        this.setRequestMethod(GET_REQUEST);
    }

    /**
     * Set the request method using a String value, to be used when request is not Post or Get
     *
     * @param method The http method as a String
     * @throws ProtocolException
     */
    public void setRequestMethod(String method) throws ProtocolException {
        con.setRequestMethod(method);
    }

    /**
     * This will set the connection http headers using a map of strings
     *
     * @param headers The headers to add to the http request as a map of Strings
     */
    public void setConnectionHeaders(Map<String, String> headers) {
        if(headers != null) {
            for(Map.Entry<String, String> header : headers.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }
        }
    }

    /**
     * This will set a connection header
     *
     * @param key The key as a String
     * @param value The value as a Strings
     */
    public void setConnectionHeader(String key, String value) {
        con.setRequestProperty(key, value);
    }

    // Sending request

    /**
     * This will perform the request using the previously setup credentials
     *
     * @return The response from the http request as a string
     */
    public String sendRequest() {

        DataOutputStream wr = null;
        InputStream is = null;
        StringBuffer response = null;

        try {
            logger.info("Connecting to " + url.toString());
            con.connect();

            logger.info("Sending " + con.getRequestMethod() + " request");
            if(!data.isEmpty()) {
                wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(data);
            }

            logger.info("Recieving response....");
            is = con.getInputStream();
            response = new StringBuffer();
			/*byte[] b = new byte[2048];
			while ( is.read(b) != -1) {
				response.append(new String(b));
			}*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while((line=reader.readLine())!=null){
                response.append(line + NEW_LINE);
            }

        } catch (IOException e) {
            logger.warn("Error in sending/receiving request");
            logger.warn(e.getMessage());
        } finally {
            logger.info("Disconnecting");
            con.disconnect();
            try {
                if(wr != null) {
                    wr.flush();
                    wr.close();
                }
                if(is != null) {
                    is.close();
                }
            } catch (IOException e) {
                // Ignore
            }
        }

        if(response != null) {
            return response.toString();
        } else {
            return "";
        }
    }

    // After request

    /**
     * This will get the response code from the http request
     *
     * @return The response code or -1 if not set
     */
    public int getResponseCode() {
        int code = -1;
        try {
            code = con.getResponseCode();
        } catch (IOException e) {
            logger.warn("Http response code not available");
            logger.warn(e.getMessage());
        }
        return code;
    }
}

