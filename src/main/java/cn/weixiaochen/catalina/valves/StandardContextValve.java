package cn.weixiaochen.catalina.valves;

import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 0914xc 2022/1/3
 */
public class StandardContextValve extends ValveBase {

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        request.getWrapper().getPipeline().getFirst().invoke(request, response);
    }
}
