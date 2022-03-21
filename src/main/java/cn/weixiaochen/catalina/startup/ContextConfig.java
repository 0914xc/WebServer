package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.Context;
import cn.weixiaochen.catalina.Lifecycle;
import cn.weixiaochen.catalina.LifecycleEvent;
import cn.weixiaochen.catalina.LifecycleListener;
import cn.weixiaochen.catalina.tomcat.web.WebRuleSet;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @author 0914xc 2022/3/14
 */
public class ContextConfig implements LifecycleListener {

    protected Context context = null;

    private final static Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        // Identify the context we are associated with
        try {
            context = (Context) event.getLifecycle();
        } catch (ClassCastException e) {
            logger.error("Lifecycle event data object [{}] is not a Context", event.getLifecycle());
            return;
        }

        // Process the event that has occurred
        if (event.getType().equals(Lifecycle.CONFIGURE_START_EVENT)) {
            configureStart();
        }
    }

    /**
     * Process a "contextConfig" event for this Context
     */
    protected void configureStart() {
        webConfig();
    }

    /**
     * Scan the web.xml that apply to the web application
     */
    protected void webConfig() {
        Digester webDigester = createWebDigester();

        // Process the default and application web.xml files
        defaultConfig(webDigester);
        applicationConfig(webDigester);
    }

    private Digester createWebDigester() {
        Digester webDigester = new Digester();
        webDigester.addRuleSet(new WebRuleSet());
        return webDigester;
    }

    /**
     * Process the default configuration file, if it exists.
     */
    private void defaultConfig(Digester webDigester) {
        File file = new File(Constants.DefaultWebXml);
        // todo: process the default web.xml file
    }

    /**
     * Process the application configuration file, if it exists.
     */
    private void applicationConfig(Digester webDigester) {

        // Open the application web.xml file, if it exists
        File file = new File(context.getPath() + Constants.ApplicationWebXml);
        if (!file.exists()) {
            logger.info("Missing application web.xml, using defaults only");
            return;
        }

        // Process the application web.xml file
        webDigester.clear();
        webDigester.push(context);
        try {
            webDigester.parse(file);
        } catch (IOException | SAXException e) {
            logger.error("Parse error in application web.xml");
        }
    }


}
