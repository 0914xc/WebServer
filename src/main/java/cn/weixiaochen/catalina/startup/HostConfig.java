package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.core.StandardContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author 0914xc 2022/3/15
 */
public class HostConfig implements LifecycleListener {

    private final static Logger logger = LoggerFactory.getLogger(HostConfig.class);

    /**
     * The Host we are associated with.
     */
    protected Host host = null;

    /**
     * Process the START event for an associated Host.
     */
    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        // Identify the host we are associated with
        try {
            host = (Host) event.getLifecycle();
        } catch (ClassCastException e) {
            logger.error("Lifecycle event data object [{}] is not a Host", event.getLifecycle());
            return;
        }

        // Process the event that has occurred
        if (event.getType().equals(Lifecycle.START_EVENT)) {
            start();
        }
    }

    /**
     * Process a "start" event for this Host.
     */
    protected void start() {
        deployApps();
    }


    protected void deployApps() {
        File appBase = host.getAppBaseFile();
        File[] webapps = appBase.listFiles();

        if (webapps == null) {
            return;
        }

        for (File file : webapps) {
            if (file.isDirectory()) {
                Context context = new StandardContext();
                context.setName(file.getName()); // 默认context的名字为web文件夹的名字
                context.setPath(file.getPath());
                context.addLifecycleListener(new ContextConfig());
                host.addChild(context);
            }
        }
    }
}
