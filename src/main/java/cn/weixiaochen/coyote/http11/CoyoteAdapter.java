package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.catalina.connector.Connector;
import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;
import cn.weixiaochen.coyote.Adapter;

/**
 * @author 0914xc 2022/3/6
 */
public class CoyoteAdapter implements Adapter {

    private Connector connector;

    public CoyoteAdapter(Connector connector) {
        this.connector = connector;
    }

    @Override
    public Connector getConnector() {
        return this.connector;
    }

    @Override
    public void setConnector(Connector connector) {
        this.connector = connector;
    }

    @Override
    public void service(cn.weixiaochen.coyote.Request req, cn.weixiaochen.coyote.Response res) throws Exception {
        // Create objects
        Request request = connector.createRequest();
        request.setCoyoteRequest(req);
        Response response = connector.createResponse();
        response.setCoyoteResponse(res);

        // Link objects
        request.setResponse(response);
        response.setRequest(request);

        // Calling the container
        connector.getService().getContainer().getPipeline().getFirst().invoke(request, response);
    }
}
