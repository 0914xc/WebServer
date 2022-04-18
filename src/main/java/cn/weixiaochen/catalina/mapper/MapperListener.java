package cn.weixiaochen.catalina.mapper;

import cn.weixiaochen.catalina.*;

/**
 * @author 0914xc 2022/4/5
 */
public class MapperListener extends LifecycleBase implements LifecycleListener {

    /**
     * Associated service.
     */
    private final Service service;

    /**
     * Associated mapper.
     */
    private final Mapper mapper;

    public MapperListener(Service service) {
        this.service = service;
        this.mapper = service.getMapper();
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {
        // If the engine does not exist, then need not to start mapper listener.
        Engine engine = service.getContainer();
        if (engine == null) {
            return;
        }

        Container[] conHosts = engine.findChildren();
        for (Container conHost : conHosts) {
            Host host = (Host) conHost;
            // Registering the host will register the context and wrappers.
            registerHost(host);
        }

    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    @Override
    public void lifecycleEvent(LifecycleEvent event) {

    }

    /**
     * Register host
     */
    private void registerHost(Host host) {
        mapper.addHost(host.getName(), host);
        for (Container container : host.findChildren()) {
            registerContext((Context) container);
        }
    }

    /**
     * Register context
     */
    private void registerContext(Context context) {
        String contextPath = context.getPath();
        if ("/".equals(contextPath)) {
            contextPath = "";
        }

        Host host = (Host) context.getParent();
        mapper.addContext(host.getName(), host, contextPath, context);

        for (Container container : context.findChildren()) {
            registerWrapper((Wrapper) container);
        }
    }

    /**
     * Register Wrapper
     */
    private void registerWrapper(Wrapper wrapper) {
        mapper.addWrapper(wrapper.getName(), wrapper);
    }
}
