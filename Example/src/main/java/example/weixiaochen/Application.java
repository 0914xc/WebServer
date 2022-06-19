package example.weixiaochen;

import cn.weixiaochen.HttpServer;

/**
 * @author 0914xc 2022/6/18
 */
public class Application {

    public static void main(String[] args) throws Exception {
        HttpServer server = new HttpServer();
        server.start();
    }
}
