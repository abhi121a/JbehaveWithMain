package org.testing.framework.backend.Databases.mySql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

import com.jcraft.jsch.JSchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testing.framework.model.DBModels.MySqlModel;

/**
 * Created by Ravinder Singh on 12-11-2015.
 */
public class MySqlActions {

    static Logger logger = LoggerFactory.getLogger(MySqlActions.class.getName());
    private DataSource dataSource;
    private MySqlConnectionOverSSH mysqlConnectionOverSSH ;

    //Constructor for connections over SSH
    /*public MySqlActions(DataSource dataSource,
                        MySqlConnectionOverSSH mysqlConnectionOverSSH) {
        this.dataSource = dataSource;
        this.mysqlConnectionOverSSH = mysqlConnectionOverSSH;
        mysqlConnectionOverSSH.sshConnect();
    }*/

    //Constructor for Direct connection over public IP
    public MySqlActions(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //Execute query as specified in parameter
    public List<HashMap<String,Object>> executeSelectQuery(String query) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //Get the connection details and execute the query
            conn = dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rset = stmt.executeQuery(query);
            return convertResultSetToList(rset);
        } catch (SQLException e) {
            logger.info("error encountered");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return null;
    }

    //Convert ResultSet to a List of HashMaps
    public List<HashMap<String,Object>> convertResultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
        int columns = md.getColumnCount();
        List<HashMap<String,Object>> list = new ArrayList<HashMap<String,Object>>();

        while (rs.next()) {
            HashMap<String,Object> row = new HashMap<String, Object>(columns);
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
            }
            list.add(row);
        }
        return list;
    }

    public HashMap<String,Object> executeSelectQueryResultingHashMaps(String query) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            //Get the connection details and execute the query
            conn = dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rset = stmt.executeQuery(query);
            return convertResultSetToHashMaps(rset);
        } catch (SQLException e) {
            logger.info("error encountered");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return null;
    }

    public HashMap<String,Object> convertResultSetToHashMaps(ResultSet rs) throws SQLException {
        ResultSetMetaData md = (ResultSetMetaData) rs.getMetaData();
        int rowCount=0;
        if (rs.last()) {
            rowCount = rs.getRow();
            rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
        }
        int columns = md.getColumnCount();
        HashMap<String,Object> row = new HashMap<String, Object>(rowCount);

        while (rs.next()) {
            for(int i=1; i<=columns; ++i) {
                row.put(md.getColumnName(i),rs.getObject(i));
                logger.info(row.toString());
            }
        }
        return row;
    }


    /**
     * creating an method for running a data Manipulation like insert, update and delete.
     * @param query Query Need to be executed
     * @return 1 if update query is success otherwise null
     */
    public Integer executeUpdateQuery(String query) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //Get the connection details and execute the query
            conn = dataSource.getConnection();
            logger.info("Connection created");
            stmt = (Statement) conn.createStatement();
            logger.info("Running a query");
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info("error encountered");
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
        return null;
    }
}