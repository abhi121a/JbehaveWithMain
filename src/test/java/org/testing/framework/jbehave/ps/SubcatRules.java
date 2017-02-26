package org.testing.framework.jbehave.ps;

/**
 * Created by sakshi on 4/12/15.
 */

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import org.slf4j.LoggerFactory;

import org.testing.framework.steps.ps.HomepagesSteps;
import org.testing.framework.steps.ps.SucatRulesSteps;
import org.testing.framework.steps.dbSteps.MySqlSteps;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by sakshi on 1/12/15.
 */
public class SubcatRules {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(SubcatRules.class.getName());
    ArrayList<String> psGroupIds=new ArrayList<String>();
    ArrayList<String> actualCount=new ArrayList<String>();
    HashMap<String,String> enablePSGroupId=new HashMap<String, String>();
    HashMap<String,String> disablePSGroupId=new HashMap<String, String>();

    @Steps
    SucatRulesSteps subcatRulesStep;

    @Steps
    HomepagesSteps homepagesSteps;

    @Steps
    MySqlSteps mySqlSteps;


    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";


    public SubcatRules() throws IOException {
        prop = new Properties();
        prop.load(SubcatRules.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if(System.getProperty("env")!= null)
            prop.load(SubcatRules.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(SubcatRules.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }


    @When("I click on the button $button")
    public void clickOnButton(String button) throws Exception{
        logger.info("click submit button" );
        subcatRulesStep.click_the_button(button);
        Thread.sleep(10000);
        subcatRulesStep.page_refresh();

    }

    @When("I click on link $link")
    public void clickOnLinkorText(String link) throws Exception{
        logger.info("Clicking on the text/link" + link);
        homepagesSteps.click_the_link(link);
        logger.info("CLICKED");
    }

    @When("I click on rule $rule")
    public void clickOnRule(String rule)throws Exception{
        logger.info("Clicking on the rule" + rule);
        subcatRulesStep.click_the_link(rule);
        logger.info("CLICKED");
    }

    @When("I select from dropdown for $field with $value")
    public void selectFromDropDown(String field,String value)throws Exception{
        logger.info("Select A value " + value+ "from dropdown");
        subcatRulesStep.select_value(value,field);
        logger.info("SELECTED");
    }

    @When("I type values $value in fields $field in $row")
    public void typeTheValue(String value,String field,String row) throws Exception{
        logger.info("Enter values of Cm,gm,md etc in the"+row);
        String fields[]=field.split("_");
        String values[]=value.split("_");
        for(int i=0;i<fields.length;i++) {
            subcatRulesStep.type_the_value(values[i],row,fields[i]);
        }
        logger.info("VALUES ARE TYED");
    }

//    @When("I get the actual results from ps UI FOR row $rowindex1 $rowindex2 $rowindex3")
//    public void getResultsFromPS(String rowindex1,String rowindex2,String rowindex3)throws Exception{
//        logger.info("GET VENDOR SUPC count " + rowindex1+ "from Price Simulator");
//        subcatRulesStep.getTextFromElement(rowindex1);
//
//
//    }


    @Then("I validate field $field if present")
    public void IfElementPresent(String field) throws Exception{
        String elements[]=field.split("_");
        for(int i=0;i<elements.length;i++) {
            logger.info("If Element"+elements[i]+"is present" );
            subcatRulesStep.assert_click_element_present(elements[i]);
        }
        logger.info("ELEMENT PRESENT.VALIDATION COMPLETE");
    }

    @Then("I click the $button button and check for alert message $msg")
    public void clickOnButtonAndCheckAlert(String button,String message) throws Exception{
        logger.info("click submit button and check for alert");
        subcatRulesStep.click_the_link_and_check_alert(button,message);
    }

    @Then("I click the $button button for $SUBCATEGORY_NAME and check for alert message $msg")
    public void clickOnPolyButtonAndCheckAlert(String button,String subcat,String message) throws Exception{
        logger.info("click submit button and check for alert");
        subcatRulesStep.click_the_link_and_check_alert(button,subcat,message);
        try{
            Thread.sleep(10000);
        }catch(Exception e){}
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
    }


    @Then("Check for Message $msg")
    public void checkForMsg(String msg) throws Exception{
        subcatRulesStep.assert_that_there_is_text_present_at_element(msg);
    }
    @Then("I validate if mail triggered")
    public void checkForMAIL(String msg) throws Exception{
        logger.info("CHECK FOR MAIL");
    }

    @Then("I validate My Sql DB results for subcatrule using query $query with $value")
    public void validateMySqlPSgroup(String query,String value) throws Exception {
        try{
            Thread.sleep(10000);
        }
        catch(Exception e){}

        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$", value);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
        if(!(queryResults.isEmpty())) {
            logger.info("Result Row " + queryResults.get(0));
            for (int i = 0; i < queryResults.size(); i++) {
                logger.info("GroupId" + queryResults.get(i).get("GroupId"));
                psGroupIds.add(queryResults.get(i).get("GroupId").toString());
                String status = queryResults.get(i).get("Status").toString();
                logger.info("status" + queryResults.get(i).get("Status").toString());
                if (status.equalsIgnoreCase("-1")) {
                    logger.info("STATUS IS -1 BEFORE CASHBACK");
                } else {
                    throw new Exception("STATUS IS NOT -1");
                }
            }
        }
        else{
            logger.info("Query result is empty");
        }
    }

    @Then("I validate My Sql DB results for viewSummary using query $query")
    public void validateMySqlPSUserInput(String query) throws Exception {
        try{
            Thread.sleep(5000);
        }
        catch(Exception e){}
        logger.info("length of group ids"+psGroupIds.size());
        for(int i=0;i<psGroupIds.size();i++) {
            actualCount.add(subcatRulesStep.getTextFromElement("ROWINDEX"+(i+1)));
            logger.info(actualCount.get(i));
            logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
            String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
            sqlquery = sqlquery.replace("$$$", psGroupIds.get(i));
            logger.info("NEW QUERY IS" + sqlquery);
            List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
            logger.info("GroupId" + queryResults.get(0).get("GroupId"));
            logger.info("SIZE" + queryResults.size());
            Integer ExpectedCount=queryResults.size();

            if(actualCount.get(i).equals(ExpectedCount.toString())) {
                logger.info("RESULTS FROM MYSQL VALIDATED");
            }
            else{
                throw new Exception("Mysql count validation failed");
            }
        }
    }

    @Then("I validate $subcatId Status From My SQL using query $query with status $status")
    public void validateMySqlSubcatIDstatus(String subcatId,String query,String status) throws Exception {

        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$", subcatId);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
        logger.info("STATUS IS" + status);
        if(status.equalsIgnoreCase("ENABLE")){
            queryResults.get(0).get("Status").equals(1);
        }else{
            queryResults.get(0).get("Status").equals(0);
        }
    }

    @Then("I validate All PSGroups Status with $SubcatId From MY SQL using query $query status $status")
    public void validateMySqlPSgroupStatus(String subcatId,String query,String status) throws Exception {
        try{
            Thread.sleep(10000);
        }
        catch(Exception e){}
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$", subcatId);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);

        if(status.equalsIgnoreCase("ENABLE")){
            for(int i=0;i<queryResults.size();i++){
                logger.info("hieeeeeeee");
                if(queryResults.get(i).get("Status").toString().equals("-1") || queryResults.get(i).get("Status").toString().equals("1")) {
                    enablePSGroupId.put(queryResults.get(i).get("GroupId").toString(), queryResults.get(i).get("Status").toString());
                }
                else{
                    throw new Exception("status not 1/-1...error");
                }
                }
        }
        if(status.equalsIgnoreCase("DISABLE")){
            for(int i=0;i<queryResults.size();i++) {
                if(queryResults.get(i).get("Status").toString().equals("-1") || queryResults.get(i).get("Status").toString().equals("1")) {
                    disablePSGroupId.put(queryResults.get(i).get("GroupId").toString(), queryResults.get(i).get("Status").toString());
                }
                else{
                    throw new Exception("status not 1/-1...error");
                }
            }
            comparePSGroupStatus();
        }

    }

    public void comparePSGroupStatus() throws Exception{
        for(int i=0;i<enablePSGroupId.size();i++){
            if(enablePSGroupId.get("GroupId").equals(disablePSGroupId.get("GroupId"))) {
                if (enablePSGroupId.get("Status").equals("1")) {
                    disablePSGroupId.get("Status").equals("0");
                }
                if(enablePSGroupId.get("Status").equals("-1")) {
                    disablePSGroupId.get("Status").equals("-6");
                }
                else{
                    throw new Exception("sql db enable/disable status fail");
                }

            }
            else{
                throw new Exception("group ids are not same");
            }
        }

    }

    @When("I click on $button button on rfs page for $SUPC")
    public void clickButton(String button,String supc) throws Exception{
        logger.info("click submit button" );
        subcatRulesStep.click_the_button(button,supc);
    }

    @Then("I validate My Sql DB results for remove from simulator using query $query with $supc if $status")
    public void validateRfsStatus(String query,String supc,String status) throws Exception {
        try{
            Thread.sleep(10000);
        }
        catch(Exception e){}

        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$",supc);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);

        if(status.equalsIgnoreCase("on")){
            if(queryResults.get(0).get("Status").toString().equals("0")){
                logger.info("status is changed to 0 after rfs");
            }
        }
        if(status.equalsIgnoreCase("off")){
            if(queryResults.get(0).get("Status").toString().equals("-1")){
                logger.info("status is changed to previous state -1");
            }

        }
    }

    @Then("I validate My Sql DB results for set mop for products using query $query with $supc if $status with $mop")
    public void validateSetMOP(String query,String supc,String status,String mop) throws Exception {
        try{
            Thread.sleep(10000);
        }
        catch(Exception e){}

        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$",supc);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);

        if(status.equalsIgnoreCase("on")){
            if(queryResults.get(0).get("supc").toString().equals(mop)){
                logger.info("mop is set");
            }
        }
        if(status.equalsIgnoreCase("off")){
            if(queryResults.equals(null)){
                logger.info("mop is null after deletion");
            }

        }
    }


    @When("Upload file from $location after click button $choose file")
    public void uploadAFile(String choose,String location) throws Exception {
        logger.info("Upload file from location "+ location);
        logger.info("I click on choose button"+choose);
        subcatRulesStep.click_the_button(choose);

        StringSelection ss = new StringSelection(location);
        //StringSelection ss = new StringSelection("/home/sakshi/Downloads/mop.xlsx");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        //imitate mouse events like ENTER, CTRL+C, CTRL+V
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.delay(3000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        // C:\Users\abhishek.verma02\Desktop/productlist.xlsx
    }

    @When("I get size from table $tablename row identifier $row")
    public Integer getTableSize(String tablename,String row) throws  Exception{
        Integer size=subcatRulesStep.getTableSize(tablename,row);
        logger.info("size of the table"+size);
        return  size;
    }

    @Then("I validate the SUPC-vendor count using query $query")
    public void FilterValidSUPC(String query,String subcat,String tableName) throws Exception{
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$",subcat);
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);

        subcatRulesStep.assert_table_cell_element(tableName,1,3,Integer.toString(queryResults.size()));


    }

    @When("I tick $checkbox $rowNo")
    public void checkboxOn(String checkbox,String rows) throws Exception{
        String arrRows[]= rows.split("_");
        for(int i=0;i<arrRows.length;i++){
            subcatRulesStep.tick_the_checkbox(checkbox,arrRows[i]);
        }

    }
    @When("I tick $checkbox")
    public void headcheckbox(String checkbox) throws Exception{
        //String arrRows[]= rows.split("_");
        //for(int i=0;i<arrRows.length;i++){
            subcatRulesStep.tick_the_checkbox(checkbox);
        }



}