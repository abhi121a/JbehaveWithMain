package org.testing.framework.backend.Databases.mySql;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ravinder.singh04 on 12-11-2015.
 */
public class MySqlConnectionOverSSH {
    static Logger logger = LoggerFactory.getLogger(MySqlConnectionOverSSH.class.getName());

    private String sshUser;
    private String sshHost;
    private String sshIdentity;
    private Integer sshPort;
    private Integer localPort;
    private Integer remotePort;
    private String remoteHost;
    private Session session = null;

    public String getSshHost() {
        return sshHost;
    }

    public void setSshHost(String sshHost) {
        this.sshHost = sshHost;
    }

    public String getSshUser() {
        return sshUser;
    }

    public void setSshUser(String sshUser) {
        this.sshUser = sshUser;
    }

    public String getSshIdentity() {
        return sshIdentity;
    }

    public void setSshIdentity(String sshIdentity) {
        this.sshIdentity = sshIdentity;
    }

    public Integer getSshPort() {
        return sshPort;
    }

    public void setSshPort(Integer sshPort) {
        this.sshPort = sshPort;
    }

    public Integer getLocalPort() {
        return localPort;
    }

    public void setLocalPort(Integer localPort) {
        this.localPort = localPort;
    }

    public Integer getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(Integer remotePort) {
        this.remotePort = remotePort;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public void sshConnect() {

        if(session == null) {
            try {
                JSch jsch = new JSch();
                jsch.addIdentity(sshIdentity);
                logger.info("identity added");

                Session session = jsch.getSession(sshUser, sshHost, sshPort);
                logger.info("session created");

                java.util.Properties config = new java.util.Properties();
                config.put("StrictHostKeyChecking", "no");
                session.setConfig(config);

                session.connect();
                logger.info("session connected.....");

                int assignedPort = session.setPortForwardingL(localPort, remoteHost, remotePort);


            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (session != null && session.isConnected()) {
                    logger.info("Closing SSH Connection");
                    session.disconnect();
                }
            }
        }
    }
}