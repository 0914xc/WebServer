package cn.weixiaochen.catalina;

import cn.weixiaochen.catalina.connector.Request;
import cn.weixiaochen.catalina.connector.Response;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author 0914xc 2021/12/12
 */
public interface Valve {

    /**
     * @return the next Valve in the pipeline containing this Valve, if any.
     */
    Valve getNext();

    /**
     * Set the next Valve in the pipeline containing this Valve.
     *
     * @param valve The new next valve, or <code>null</code> if none
     */
    void setNext(Valve valve);

    /**
     * Perform request processing as required by this Value
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    void invoke(Request request, Response response) throws IOException, ServletException;
}
