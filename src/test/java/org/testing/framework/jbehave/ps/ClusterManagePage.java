package org.testing.framework.jbehave.ps;

import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testing.framework.properties.LoadProjectProperties;
import org.testing.framework.steps.ps.ManageClusterSteps;
import org.testing.framework.steps.dbSteps.MySqlSteps;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * Created by abhishek.verma02 on 18-11-2015.
 */
public class ClusterManagePage {


    static Logger logger = LoggerFactory.getLogger(Login_ps.class.getName());
    String cm_min;
    float cm_max_test;
    String cm_max;
    float cm_min_test;
    String subcategory_id;
    float v1;
    float v2;



    @Steps
    ManageClusterSteps ManageClusterSteps;

    @Steps
    MySqlSteps mySqlSteps;


    private Properties prop = new Properties();
    private static String COMMON_PROP_LOCATION = "/properties/common.properties";
    private static String ENV_PROP_LOCATION = "/properties/";

    public ClusterManagePage() throws IOException {
        prop = new Properties();
        prop.load(ClusterManagePage.class.getResourceAsStream(COMMON_PROP_LOCATION));
        // Get environment variable then load these properties
        if(System.getProperty("env")!= null)
            prop.load(ClusterManagePage.class.getResourceAsStream(ENV_PROP_LOCATION + System.getProperty("env") + ".properties"));
        else
            prop.load(ClusterManagePage.class.getResourceAsStream(ENV_PROP_LOCATION + "at" + ".properties"));

    }

    @Then("I click cluster's $button")
    public void clickOnClusterManage(String button) throws Exception {
        logger.info("click cluster button" + button);
        // loginPageSteps.click_the_button(button);
        ManageClusterSteps.click_the_button(button);
    }

    @When("I type cluster's $value in the field $field")
    public void typeValueinField(String value, String field) throws Exception {
        logger.info("Enter cluster name " + value + "in Field  " + field);
        ManageClusterSteps.type_the_value(value, field);
    }


    @Then("I click cluster $button and check for alert message $msg")
    public void clickOnButtonAndCheckAlert(String button,String message) throws Exception{
        logger.info("click submit button and check for alert");
        ManageClusterSteps.click_the_link_and_check_alert(button,message);
    }

    @Then("I Validate cluster's $value in field $field")
    public void validatevalueinfield(String values, String replaceString) throws Exception {
        logger.info("Validate Cluster created " + values + "in Field " + replaceString);
        ManageClusterSteps.assert_element_present_from_xml_textArea(values, replaceString);
    }



 @When("I type cluster's $clustername in the field $constraints with values $clustervalue")
    public void entervaluesincluster(String clsutername,String constraints, String cluster_values){
     logger.info(clsutername+constraints+cluster_values);



 }


    @When("In ManageCluster I type values $value in fields $field in $row")
    public void typeTheValue(String value,String field,String row) throws Exception{
        logger.info("Enter values of Cm,gm,md etc in the"+row);
        String fields[]=field.split("_");
        String values[]=value.split("_");
        for(int i=0;i<fields.length;i++) {
            ManageClusterSteps.type_the_value(values[i],row,fields[i]);
        }
        logger.info("VALUES ARE TYED");
    }


// to select a drop down

    @When("I select cluster's dropdown for $field with $value")
    public void selectFromDropDown(String field,String value)throws Exception{
        logger.info("Select A value " + value+ "from dropdown");
        ManageClusterSteps.select_value(value,field);
        logger.info("SELECTED");
    }


    @Then("I validate My Sql DB results using query $query")
    public void validateMySqlDBResults(String query) throws Exception {
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(mySqlSteps.getMySqlModel(query).getQuery());

        if (!queryResults.isEmpty()) {
            logger.info("Result Row test  " + queryResults.get(0).toString());
            for (int i = 0; i < queryResults.size(); i++) {
                HashMap<String, Object> a = queryResults.get(i);
                //getting subcategory and cm gm values
                logger.info(a.get("subcategory_id").toString());
                logger.info(a.get("cm_Min").toString());
                logger.info(a.get("cm_Min").toString());
                logger.info(a.get("cm_Max").toString());
                subcategory_id = a.get("subcategory_id").toString();
                cm_min = a.get("cm_Min").toString();
                cm_max = a.get("cm_Max").toString();
                logger.info("cm_min :" + cm_min + "cm_min :" + cm_max + "subcategory_id :" + subcategory_id);
                //validatevalueinfield();

                if (subcategory_id.equals("43")) {
                    logger.info("values of cm and gm for Specified Subcat is - cm_Min :" + cm_min + "cm_max :" + cm_max);
                    cm_min_test = Float.parseFloat(a.get("cm_Min").toString());
                    cm_max_test = Float.parseFloat(a.get("cm_Max").toString());

                    logger.info("values of cm and gm for Specified Subcat is - cm_Min " + cm_max_test + "cm_max" + cm_min_test);
                }
            }
        } else
            logger.info("table is empty");// logger.info("Result Row data " + queryResults.get(i));

        //logger.info("Result Row " + queryResults.get(3).toString());


    }


    @Then("I update $wrong cluster $cmfield and $gmfield using Cluster Sql DB results using query $query")
    public void validateLimits(String valuetype, String cmfield, String gmfield, String query) throws Exception {
        logger.info("Query is :-" + mySqlSteps.getMySqlModel(query).getQuery());
        List<HashMap<String, Object>> queryResults = mySqlSteps.executeSelectQuery(mySqlSteps.getMySqlModel(query).getQuery());
        if (!queryResults.isEmpty()) {
            logger.info("Result Row test  " + queryResults.get(0).toString());
            for (int i = 0; i < queryResults.size(); i++) {
                HashMap<String, Object> a = queryResults.get(i);
                //getting subcategory and cm gm values
                logger.info(a.get("subcategory_id").toString());
                logger.info(a.get("cm").toString());
                logger.info(a.get("nc").toString());
                subcategory_id = a.get("subcategory_id").toString();
                String cm = a.get("cm").toString();
                String nc = a.get("nc").toString();
                v1 = Float.parseFloat(a.get("cm").toString());
                v2 = Float.parseFloat(a.get("nc").toString());
                if (subcategory_id.equals("43")) {
                    if (valuetype.equals("wrong")) {
                        v1--;
                        v2--;
                        logger.info(valuetype+" value entered for "+"subcategory_id :" + subcategory_id+ "  CM :" + v1 + "  NC :" + v2 );
                        //  ManageClusterSteps.type_the_value(cm, cmfield);
                        //   ManageClusterSteps.type_the_value(cm, gmfield);
                    }
                    else if(valuetype.equals("correct")){
                        v1++;
                        v2++;
                        logger.info(valuetype+" value entered for "+"subcategory_id :" + subcategory_id+ "  CM :" + v1 + "  NC :" + v2 );


                        //  ManageClusterSteps.type_the_value(cm, cmfield);
                        // ManageClusterSteps.type_the_value(cm, gmfield);
                    }
                }
            }
        } else
            logger.info("table is empty");// logger.info("Result Row data " + queryResults.get(i));

        //logger.info("Result Row " + queryResults.get(3).toString());


    }

    @Then("Upload Cluster's file from location $location after click button $choose file")
    public void uploadAFile(String location,String choose) throws Exception {
        logger.info("Upload file from location "+ location);
        logger.info("I click on choose button"+choose);
        ManageClusterSteps.click_the_button(choose);

        StringSelection ss = new StringSelection("C:\\Users\\abhishek.verma02\\Desktop\\productlist.xlsx");
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

    @When("I type Cluster rule values $value in fields $field in $row for $Clustername")
    public void typeTheValueinCluster(String value,String field,String row,String clustername) throws Exception{
        logger.info("Enter values of Cm,gm,md etc in the"+row+"for Cluster"+clustername);
        String fields[]=field.split("_");
        String values[]=value.split("_");
        for(int i=0;i<fields.length;i++) {
            logger.info(LoadProjectProperties.getStringProperty(LoadProjectProperties.REPLACE_CHARACTER));
            logger.info(LoadProjectProperties.getStringProperty(LoadProjectProperties.REPLACE_CHARACTER_2));
            logger.info(fields[i]+"----"+values[i]);
            ManageClusterSteps.type_the_value(values[i],row,fields[i],clustername);
        }
        logger.info("VALUES ARE TYED");
    }


    @Then ("For cluster $clustername I clicked button $Updatebutton and check for alert message $msg")
    public void clickOnButtonAndCeckAlertofperticularsupc(String clustername,String updateButton,String message) throws Exception {
        logger.info("click submit button and check for alert");
        ManageClusterSteps.click_the_link_and_check_alert(updateButton,clustername,message);
    }


}

