package cn.weixiaochen.catalina;


import cn.weixiaochen.catalina.connector.Connector;

/**
 * @author 魏小宸 2022/1/23
 */
public interface Service extends Lifecycle {

    void addConnector(Connector connector);

    void setContainer(Engine engine);

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
