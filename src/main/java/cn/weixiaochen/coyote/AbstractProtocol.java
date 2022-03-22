package cn.weixiaochen.coyote;

/**
 * @author 0914xc 2022/3/14
 */
public abstract class AbstractProtocol implements ProtocolHandler {

    /**
     * Endpoint that provides low-level network I/O - must be matched to the
     * ProtocolHandler implementation (ProtocolHandler using NIO, requires NIO
     * Endpoint etc.).
     */
    private final AbstractEndpoint endpoint;

    public AbstractProtocol(AbstractEndpoint endpoint) {
        this.endpoint = endpoint;

    }

    protected AbstractEndpoint getEndpoint() {
        return endpoint;
    }
    @Override
    public void init() throws Exception {
        endpoint.init();
    }

    @Override
    public void start() throws Exception {
        endpoint.start();
    }

    protected Adapter adapter;

    @Override
    public Adapter getAdapter() {
        return adapter;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    public int getPort() {
        return endpoint.getPort();
    }

    public void setPort(int port) {
        endpoint.setPort(port);
    }

    public abstract Processor createProcessor();
}
