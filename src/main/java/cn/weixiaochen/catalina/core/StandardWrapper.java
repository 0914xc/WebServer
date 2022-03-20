package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.LifecycleException;
import cn.weixiaochen.catalina.Wrapper;
import cn.weixiaochen.catalina.ContainerBase;
import cn.weixiaochen.catalina.valves.StandardWrapperValve;

import java.util.ArrayList;

/**
 * @author 0914xc 2021/12/12
 */
public class StandardWrapper extends ContainerBase implements Wrapper {

    /**
     * The fully qualified servlet class name for this servlet.
     */
    private String servletClass = null;

    /**
     * Mappings associated with the wrapper.
     */
    protected final ArrayList<String> mappings = new ArrayList<>();

    public StandardWrapper() {
        pipeline.setBasic(new StandardWrapperValve());
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {

    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    @Override
    public String getServletClass() {
        return this.servletClass;
    }

    @Override
    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    @Override
    public void addMapping(String mapping) {
        mappings.add(mapping);
    }

    @Override
    public void removeMapping(String mapping) {
        mappings.remove(mapping);
    }
}
