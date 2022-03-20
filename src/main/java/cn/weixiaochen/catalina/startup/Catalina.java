package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.Host;
import cn.weixiaochen.catalina.LifecycleException;
import cn.weixiaochen.catalina.Server;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @author 0914xc 2022/1/23
 */
public class Catalina {

    private final static Logger logger = LoggerFactory.getLogger(Catalina.class);

    /**
     * Pathname to the server configuration file.
     */
    protected String configFile = "conf/server.xml";

    protected Server server = null;

    public void setConfigFile(String file) {
        configFile = file;
    }

    public String getConfigFile() {
        return configFile;
    }

    public Catalina() {
    }

    /**
     * Start a new server instance.
     */
    public void load() {

        // Create and execute our Digester
        Digester digester = createStartDigester();

        try {
            File file = configFile();
            // Make the Catalina as the top element of the Stack
            digester.push(this);
            digester.parse(file);
        } catch (IOException | SAXException e) {
            logger.error("Unable to load server configuration from {}", getConfigFile(), e);
        }

        // start the new server
        try {
            getServer().init();
        } catch (LifecycleException e) {
            logger.error("Catalina.start", e);
        }
    }

    /**
     * Start a new server instance
     */
    public void start() {

        if (getServer() == null) {
            load();
        }

        if (getServer() == null) {
            logger.error("Cannot start server. Server instance is not configured.");
            return;
        }

        // Start the new server
        try {
            getServer().start();
        } catch (LifecycleException e) {
            logger.error("The required Server component failed to start so Tomcat is unable to start.");
        }
    }

    protected Digester createStartDigester() {
        // Initialize the digester
        Digester digester = new Digester();
        digester.setValidating(false);

        // Configure the actions we will be using
        digester.addObjectCreate("Server", "cn.weixiaochen.catalina.core.StandardServer");
        digester.addSetProperties("Server");
        digester.addSetNext("Server", "setServer", "cn.weixiaochen.catalina.Server");

        digester.addObjectCreate("Server/Service", "cn.weixiaochen.catalina.core.StandardService");
        digester.addSetProperties("Server/Service");
        digester.addSetNext("Server/Service", "addService", "cn.weixiaochen.catalina.Service");

        digester.addRule("Server/Service/Connector", new ConnectorCreateRule());
        digester.addSetProperties("Server/Service/Connector");
        digester.addSetNext("Server/Service/Connector", "addConnector", "cn.weixiaochen.catalina.connector.Connector");

        // Add RuleSets for nested elements
        digester.addRuleSet(new EngineRuleSet("Server/Service/"));
        digester.addRuleSet(new HostRuleSet("Server/Service/Engine/"));
        digester.addRuleSet(new ContextRuleSet("Server/Service/Engine/Host/"));


        return digester;
    }

    /**
     * Return a File object representing our configuration file.
     * @return the main configuration file
     */
    private File configFile() {
        File file = new File(configFile);
        if (!file.isAbsolute()) {
            file = new File(Bootstrap.getCatalinaBase(), configFile);
        }
        return file;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
