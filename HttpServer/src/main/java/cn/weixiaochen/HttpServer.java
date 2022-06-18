package cn.weixiaochen;

import cn.weixiaochen.container.Server;
import cn.weixiaochen.container.core.StandardServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Bootstrap loader for HttpServer
 *
 * @author 0914xc 2021/11/9
 */
public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public void start() throws Exception {
        try {
            Server server = new StandardServer();
            server.start();
        } catch (Throwable t) {
            logger.error("HttpServer start failed.", t);
            System.exit(1);
        }
    }

}
