package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.Lifecycle;
import cn.weixiaochen.catalina.LifecycleBase;
import cn.weixiaochen.catalina.Server;
import cn.weixiaochen.catalina.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 魏小宸 2022/1/23
 */
public class StandardServer extends LifecycleBase implements Server {

    protected List<Service> services = new ArrayList<>();

    @Override
    protected void initInternal() {
        services.forEach(Lifecycle::init);
    }

    @Override
    protected void startInternal() {
        services.forEach(Lifecycle::start);
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

    }

    @Override
    public void addService(Service service) {
        services.add(service);
    }
}
