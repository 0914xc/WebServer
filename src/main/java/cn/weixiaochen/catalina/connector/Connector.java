package cn.weixiaochen.catalina.connector;

import cn.weixiaochen.catalina.LifecycleBase;
import cn.weixiaochen.coyote.Request;
import cn.weixiaochen.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 连接器：负责创建一个服务器套接字，该套接字会等待传入的http请求
 * @author 魏小宸 2021/11/9
 */
public class Connector extends LifecycleBase {

    private static final Logger logger = LoggerFactory.getLogger(Connector.class);

    protected int port;

    public Connector() {
    }

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
