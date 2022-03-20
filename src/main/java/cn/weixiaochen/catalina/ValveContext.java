package cn.weixiaochen.catalina;

import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 0914xc 2022/3/6
 */
public interface ValveContext {

    void invokeNext(Request request, Response response) throws IOException, ServletException;
}
