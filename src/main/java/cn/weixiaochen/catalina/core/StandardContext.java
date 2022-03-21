package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.startup.Constants;
import cn.weixiaochen.catalina.tomcat.web.WebRuleSet;
import cn.weixiaochen.catalina.valves.StandardContextValve;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.HashMap;

/**
 * @author 0914xc 2021/12/12
 */
public class StandardContext extends ContainerBase implements Context {

    final static Logger logger = LoggerFactory.getLogger(StandardContext.class);

    private String path = null;

    private final HashMap<String, String> servletMappings = new HashMap<>();

    public StandardContext() {
        pipeline.setBasic(new StandardContextValve());
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {
        // Notify our interested LifecycleListeners
        fireLifecycleEvent(Lifecycle.CONFIGURE_START_EVENT, null);
    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }



    @Override
    public String getPath() {
        return path;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
    }

    public Wrapper createWrapper() {
        return new StandardWrapper();
    }

    @Override
    public String findServletMapping(String pattern) {
        return servletMappings.get(pattern);
    }

    @Override
    public String[] findServletMappings() {
        String[] results = new String[servletMappings.size()];
        return servletMappings.keySet().toArray(results);
    }

    @Override
    public void addServletMapping(String pattern, String name) {
        if (findChild(name) == null) {
            throw new IllegalArgumentException("Servlet mapping specifies an unknown servlet name[" + name + "]");
        }
        String adjustedPattern = adjustURLPattern(pattern);
        if (!validateURLPattern((adjustedPattern))) {
            throw new IllegalArgumentException("Invalid <url-pattern> [" + adjustedPattern + "] in servlet mapping");
        }

        // Add this mapping to our registered set
        String name2 = servletMappings.get(adjustedPattern);
        if (name2 != null) {
            // Don't allow more than one servlet on the same pattern
            Wrapper wrapper = (Wrapper) findChild(name2);
            wrapper.removeMapping(adjustedPattern);
        }
        servletMappings.put(adjustedPattern, name);

        Wrapper wrapper = (Wrapper) findChild(name);
        wrapper.addMapping(adjustedPattern);
    }

    /**
     * Adjust the URL pattern to begin with a leading slash, if appropriate
     * Otherwise, return the specified URL pattern unchanged.
     *
     * @param urlPattern The URL pattern to be adjusted (if needed) and returned
     * @return the URL pattern with a leading slash if needed
     */
    protected String adjustURLPattern(String urlPattern) {

        if (urlPattern == null) {
            return urlPattern;
        }
        if (urlPattern.startsWith("/") || urlPattern.startsWith("*.")) {
            return urlPattern;
        }
        return "/" + urlPattern;
    }

    protected boolean validateURLPattern(String urlPattern) {

        if (urlPattern == null) {
            return false;
        }
        if (urlPattern.indexOf('\n') >= 0 || urlPattern.indexOf('\r') >= 0) {
            return false;
        }
        if (urlPattern.equals("")) {
            return true;
        }
        if (urlPattern.startsWith("*.")) {
            return urlPattern.indexOf('/') < 0;
        }
        return urlPattern.startsWith("/") && !urlPattern.contains("*.");
    }

}
