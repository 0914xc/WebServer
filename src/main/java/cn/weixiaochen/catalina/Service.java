package cn.weixiaochen.catalina;


import cn.weixiaochen.catalina.connector.Connector;

/**
 * @author 0914xc 2022/1/23
 */
public interface Service extends Lifecycle {

    void addConnector(Connector connector);

    /**
     * @return the <code>Container</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     */
    Container getContainer();

    /**
     * Set the <code>Container</code> that handles requests for all
     * <code>Connectors</code> associated with this Service.
     *
     * @param container The new Container
     */
    void setContainer(Container container);

    /**
     * @return the <code>Server</code> with which we are associated (if any).
     */
    Server getServer();

    /**
     * Set the <code>Server</code> with which we are associated (if any).
     *
     * @param server The server that owns this Service
     */
    void setServer(Server server);

}
