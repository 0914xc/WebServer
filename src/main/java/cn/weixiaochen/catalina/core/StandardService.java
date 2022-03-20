package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.connector.Connector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 0914xc 2022/1/23
 */
public class StandardService extends LifecycleBase implements Service {

    private Server server;

    private Engine engine;

    private List<Connector> connectors = new ArrayList<>();

    @Override
    protected void initInternal() throws LifecycleException {
        engine.init();
        for (Connector connector : connectors) {
            connector.init();
        }
    }

    @Override
    protected void startInternal() throws LifecycleException {
        engine.start();
        for (Connector connector : connectors) {
            connector.start();
        }
    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    @Override
    public void addConnector(Connector connector) {
        this.connectors.add(connector);
    }

    @Override
    public Container getContainer() {
        return this.engine;
    }

    @Override
    public void setContainer(Container container) {
        if (container instanceof Engine) {
            ((Engine) container).setService(this);
            this.engine = (Engine) container;
        }
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }

}
