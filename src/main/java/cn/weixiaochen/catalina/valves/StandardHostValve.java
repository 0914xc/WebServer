package cn.weixiaochen.catalina.valves;

import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 0914xc 2022/3/6
 */
public class StandardHostValve extends ValveBase {
    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        request.getContext().getPipeline().getFirst().invoke(request, response);
    }
}
