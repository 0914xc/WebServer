package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 0914xc 2022/1/23
 */
public class StandardServer extends LifecycleBase implements Server {

    protected List<Service> services = new ArrayList<>();

    @Override
    protected void initInternal() throws LifecycleException {
        for (Service service : services) {
            service.init();
        }
    }

    @Override
    protected void startInternal() throws LifecycleException {
        for (Service service : services) {
            service.start();
        }
    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    @Override
    public void addService(Service service) {
        service.setServer(this);
        services.add(service);
    }
}
