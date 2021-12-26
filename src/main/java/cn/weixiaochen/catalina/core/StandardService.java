package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.Engine;
import cn.weixiaochen.catalina.LifecycleBase;
import cn.weixiaochen.catalina.Service;
import cn.weixiaochen.catalina.connector.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 魏小宸 2022/1/23
 */
public class StandardService extends LifecycleBase implements Service {

    protected Engine engine;

    protected List<Connector> connectors = new ArrayList<>();

    @Override
    protected void initInternal() {
        engine.init();
        connectors.forEach(LifecycleBase::init);

    }

    @Override
    protected void startInternal() {
        engine.start();
        connectors.forEach(LifecycleBase::start);
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

    }

    @Override
    public void addConnector(Connector connector) {
        this.connectors.add(connector);
    }

    @Override
    public void setContainer(Engine engine) {
        this.engine = engine;
    }
}
