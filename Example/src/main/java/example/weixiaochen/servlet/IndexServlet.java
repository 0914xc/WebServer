package example.weixiaochen.servlet;

import cn.weixiaochen.http.Response;
import cn.weixiaochen.http.Request;
import cn.weixiaochen.servlet.HttpServlet;

/**
 * @author 0914xc 2022/6/18
 */
public class IndexServlet extends HttpServlet {

    @Override
    public void doGet(Request request, Response response) {
        response.setStatusCode(200);
        response.setContentType("text/html");
        response.setContent("<h1>Hello, world!</h1>");
    }
}
