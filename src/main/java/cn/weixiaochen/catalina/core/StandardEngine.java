package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.valves.StandardEngineValve;

/**
 * @author 0914xc 2022/1/23
 */
public class StandardEngine extends ContainerBase implements Engine {

    private Service service = null;

    public StandardEngine() {
        pipeline.setBasic(new StandardEngineValve());
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {
        for (Container child : findChildren()) {
            child.start();
        }
    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    /**
     * Disallow any attempt to set a parent for this Container, since an
     * Engine is supposed to be at the top of the Container hierarchy.
     *
     * @param parent Proposed parent Container
     */
    @Override
    public void setParent(Container parent) {
        throw new IllegalArgumentException("Engine cannot have a parent Container");
    }

    @Override
    public Service getService() {
        return this.service;
    }

    @Override
    public void setService(Service service) {
        this.service = service;
    }
}
