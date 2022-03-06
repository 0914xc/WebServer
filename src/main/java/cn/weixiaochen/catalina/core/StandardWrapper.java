package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.Wrapper;
import cn.weixiaochen.catalina.ContainerBase;

import java.util.ArrayList;

/**
 * @author 魏小宸 2021/12/12
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

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {

    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

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
