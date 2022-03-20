package cn.weixiaochen.coyote;

import cn.weixiaochen.catalina.connector.Connector;

/**
 * @author 0914xc 2022/3/6
 */
public interface Adapter {

    Connector getConnector();

    void setConnector(Connector connector);

    void service(Request request, Response response) throws Exception;
}
