package cn.weixiaochen.catalina.valves;

import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 0914xc 2021/12/12
 */
public class StandardWrapperValve extends ValveBase {

    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        // todo load servlet
    }
}
