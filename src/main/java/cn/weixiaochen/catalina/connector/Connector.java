package cn.weixiaochen.catalina.connector;

import cn.weixiaochen.catalina.LifecycleException;
import cn.weixiaochen.catalina.LifecycleBase;
import cn.weixiaochen.catalina.Server;
import cn.weixiaochen.catalina.Service;
import cn.weixiaochen.coyote.AbstractProtocol;
import cn.weixiaochen.coyote.Adapter;
import cn.weixiaochen.coyote.ProtocolHandler;
import cn.weixiaochen.coyote.http11.CoyoteAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接器：负责创建一个服务器套接字，该套接字会等待传入的http请求
 * @author 0914xc 2021/11/9
 */
public class Connector extends LifecycleBase {

    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    private Service service;

    private Server server;

    /**
     * The port number on which we listen for requests.
     */
    protected int port = -1;

    /**
     * Coyote protocol handler.
     */
    private ProtocolHandler protocolHandler;

    /**
     * Coyote adapter.
     */
    protected Adapter adapter = null;

    public Connector() {
        this(null);
    }

    public Connector(String protocol) {
        try {
            this.protocolHandler = ProtocolHandler.create(protocol);
        } catch (Exception e) {
            logger.error("Protocol handler instantiation failed", e);
        }
    }

    @Override
    protected void initInternal() throws LifecycleException {
        // Initialize adapter
        adapter = new CoyoteAdapter(this);
        protocolHandler.setAdapter(adapter);

        try {
            protocolHandler.init();
        } catch (Exception e) {
            throw new LifecycleException("Protocol handler initialization failed");
        }
    }

    @Override
    protected void startInternal() throws LifecycleException {
        try {
            protocolHandler.start();
        } catch (Exception e) {
            throw new LifecycleException("Protocol handler start failed");
        }
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

    }

    public Request createRequest() {
        return new Request();
    }

    public Response createResponse() {
        return new Response();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        ((AbstractProtocol)protocolHandler).setPort(port);
    }
}
