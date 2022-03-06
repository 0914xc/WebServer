package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;

/**
 * @author 魏小宸 2022/1/23
 */
public class StandardEngine extends ContainerBase implements Engine {

    private Service service = null;

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        for (Container child : findChildren()) {
            child.start();
        }
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

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
