package cn.weixiaochen.coyote;

/**
 * @author 0914xc 2022/3/13
 */
public abstract class AbstractEndpoint {

    public abstract void bind() throws Exception;
    public abstract void startInternal() throws Exception;

    public void init() throws Exception {
        bind();
    }

    public void start() throws Exception {
        startInternal();
    }

    /**
     * Server socket port.
     */
    private int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
