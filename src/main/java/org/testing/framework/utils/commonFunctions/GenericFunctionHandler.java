package org.testing.framework.utils.commonFunctions;

import org.apache.commons.lang.RandomStringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class GenericFunctionHandler {

    static Logger logger = LoggerFactory.getLogger(GenericFunctionHandler.class.getName());

    private final static String NEW_LINE = "\n";

    private GenericFunctionHandler(){}

    /**
     * This will generate a random number between a given range
     *
     * @param min The minimum value in the range as an int
     * @param max The maximum value in the range as an int
     * @return
     */
    public static int generateRandomNumberInRange(int min, int max) {

        // Create a random variable
        Random rand = new Random();

        // Generate a random number using the rand variable
        // nextInt is normally exclusive of the top value so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static String generateRandomMail() {

        //Generate a random alphabetic string within the given range

        String email = "test."+ RandomStringUtils.randomAlphabetic(5)+"@at.com";
        logger.info("generated email: " + email);
        return email;
    }

    /**
     * This will return an array of lines from a string
     *
     * @param string The string to split into lines
     * @return
     */
    public static String[] getLinesFromString(String string) {
        if(string.isEmpty()) {
            return new String[0];
        } else {
            return string.split(NEW_LINE, -1);
        }
    }

    /**
     * This will return an ArrayList of lines from a string
     *
     * @param string The string to split into lines
     * @return
     */
    public static ArrayList<String> getLinesFromStringAsList(String string) {
        return getStringAsList(string, NEW_LINE);
    }

    /**
     * This will return an ArrayList from a string using the split char
     *
     * @param string The string to split into lines
     * @param splitChar The charater to use in the split process
     * @return
     */
    public static ArrayList<String> getStringAsList(String string, String splitChar) {
        if(string.isEmpty()) {
            return new ArrayList<String>();
        } else {
            return new ArrayList<String>(Arrays.asList(string.split(splitChar, -1)));
        }
    }

    /**
     * This will return the current timestamp as a String
     * @return
     */
    public static String getTimeStamp() {
        return String.valueOf(new Date().getTime());
    }
}
