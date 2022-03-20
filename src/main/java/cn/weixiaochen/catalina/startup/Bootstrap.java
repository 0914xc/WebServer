package cn.weixiaochen.catalina.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;

/**
 * @author 0914xc 2021/11/9
 */
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    /**
     * Bootstrap 守护进程对象
     */
    private static volatile Bootstrap daemon = null;

    /**
     * catalina 守护进程对象
     */
    private Object catalinaDaemon = null;

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.init();
        } catch (Throwable t) {
            logger.error("Bootstrap init failed.", t);
        }

        daemon = bootstrap;

        try {
            daemon.load();
            daemon.start();
        } catch (Throwable t) {
            logger.error("Bootstrap start failed.", t);
            System.exit(1);
        }
    }

    public void init() throws Exception {
        Class<?> startupClass = this.getClass().getClassLoader().loadClass("cn.weixiaochen.catalina.startup.Catalina");
        catalinaDaemon = startupClass.getConstructor().newInstance();
    }

    public void load() throws Exception {
        Method method = catalinaDaemon.getClass().getMethod("load");
        method.invoke(catalinaDaemon, (Object[])null);
    }

    public void start() throws Exception {
        Method method = catalinaDaemon.getClass().getMethod("start");
        method.invoke(catalinaDaemon, (Object[])null);
    }

    public static String getCatalinaBase() {
        return Bootstrap.class.getResource("/").getPath()
                .replace("/target/classes", "");
    }
}
