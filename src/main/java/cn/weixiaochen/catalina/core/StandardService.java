package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.connector.Connector;
import cn.weixiaochen.catalina.mapper.MapperListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 0914xc 2022/1/23
 */
public class StandardService extends LifecycleBase implements Service {

    private Server server;

    private Engine engine;

    /**
     * The set of Connectors associated with this Service.
     */
    private final List<Connector> connectors = new ArrayList<>();

    /**
     * Mapper
     */
    private final Mapper mapper = new Mapper();


    private final MapperListener mapperListener = new MapperListener(this);


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
        connector.setService(this);
        connectors.add(connector);
    }

    @Override
    public Engine getContainer() {
        return this.engine;
    }

    @Override
    public void setContainer(Engine engine) {
        engine.setService(this);
        this.engine = engine;
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}
