package org.testing.framework.jbehave.ps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.testing.framework.steps.dbSteps.MySqlSteps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by sakshi on 3/12/15.
 */
public class DatabaseQueryRun {
    static Logger logger = LoggerFactory.getLogger(DatabaseQueryRun.class.getName());
    ArrayList<String> psGroupIds = new ArrayList<String>();
    ArrayList<String> actualCount = new ArrayList<String>();
    HashMap<String, String> enablePSGroupId = new HashMap<String, String>();
    HashMap<String, String> disablePSGroupId = new HashMap<String, String>();


    @Steps
    MySqlSteps mySqlSteps;


    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";

    public DatabaseQueryRun() throws IOException {
        prop = new Properties();
        prop.load(DatabaseQueryRun.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if (System.getProperty("env") != null)
            prop.load(DatabaseQueryRun.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(DatabaseQueryRun.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }

    @When("I validate My Sql DB results for viewSummary using query $query")
    public void validateMySqlPSUserInput(String query, String value) throws Exception {
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
        sqlquery = sqlquery.replace("$$$", "1008");
        logger.info("NEW QUERY IS" + sqlquery);
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
        logger.info("GroupId" + queryResults.get(0).get("GroupId"));
        logger.info("SIZE" + queryResults.size());
        Integer ExpectedCount = queryResults.size();
        if ("823".equals(ExpectedCount.toString())) {
            logger.info("RESULTS FROM MYSQL VALIDATED");
        } else {
            throw new Exception("Mysql count validation failed");
        }
    }

    @When("Update external Update time in PS_group for using $query")
    public void updateQueryExecuter(String query) throws Exception {
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        mySqlSteps.executeUpdateQuery(mySqlSteps.getMySqlModel(query).getQuery());
    }


    @When("Update external Update time for $supc in PS_group for using $query")
    public void batchCbSellerOutputForSupc(String supc, String query){
      try{
          logger.info("Query is :-" + mySqlSteps.getMySqlMap().get("CategoryForSupc").getQuery());
            String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
            sqlquery = sqlquery.replace("$$$", supc);
            logger.info("NEW QUERY IS :-" + sqlquery);
            logger.info("Running Query :-");
            List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
          if (!queryResults.isEmpty()) {
              for (int i = 0; i < queryResults.size(); i++) {
                  HashMap<String, Object> a = queryResults.get(i);
                  logger.info(" supc -"+ a.get("supc").toString()+" seller_code -"+a.get("seller_code").toString()+" cash_back -"+a.get("cash_back").toString());

              }

          }
      } catch(Exception e) {
            e.printStackTrace();
            logger.info("Unable to Run the Query");
        }

    }





    @When("Update### external Update time for $supc in PS_group for using $query")
    public void UpdateQueryforspecificSUPC(String supc, String query) throws Exception {
        //Getting Query for finding the category of an supc
        try {String[] a={"a","b"};
            replaceStringinAQuery(query,a);
            logger.info("Query is :-" + mySqlSteps.getMySqlMap().get("CategoryForSupc").getQuery());
            String sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
            sqlquery = sqlquery.replace("$$$", supc);
            logger.info("NEW QUERY IS :-" + sqlquery);
            logger.info("Running Query :-");
            List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(sqlquery);
            // Output column for the query are product_category_id.name
            String category_id = queryResults.get(0).get("product_category_id").toString();
            logger.info("Category id for SUPC is" + category_id);
            logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Unable to Run the Query");
        }

    }
// function to replace values for n no. of values in a Query denoted by $$$
    public String replaceStringinAQuery(String query,String[] a) {
        String sqlquery="";
        try {
            logger.info("Query is :-" + mySqlSteps.getMySqlMap().get(query).getQuery());
            sqlquery = mySqlSteps.getMySqlModel(query).getQuery();
            int c=0;
            while(sqlquery.contains("$$$"))
            {c++;
                sqlquery = sqlquery.replace("$$$","X");
            }

            logger.info("count of Replacable values"+c);
            //  logger.info("NEW QUERY IS :-" + sqlquery);
           // logger.info("Running Query :-");


        } catch (Exception e) {
            e.printStackTrace();
        }


        return sqlquery;
    }
}

/*

    */
/**
     * Sakshi
     *
     * @param query
     * @param value
     * @throws Exception
     *//*

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

*/



