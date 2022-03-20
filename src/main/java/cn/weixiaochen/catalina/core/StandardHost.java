package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import cn.weixiaochen.catalina.startup.ContextConfig;
import cn.weixiaochen.catalina.valves.StandardHostValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * @author 0914xc 2022/1/23
 */
public class StandardHost extends ContainerBase implements Host {

    final static Logger logger = LoggerFactory.getLogger(StandardHost.class);

    protected String appBase = "webapps";

    protected File appBaseFile = null;

    public StandardHost() throws LifecycleException {
        pipeline.setBasic(new StandardHostValve());
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {
        // Notify our interested LifecycleListeners
//        fireLifecycleEvent(Lifecycle.CONFIGURE_START_EVENT, null);
        setState(LifecycleState.STARTING);
        try {

            for (Container child : findChildren()) {
                child.start();
            }
        } catch (Exception e) {
            logger.error("webapps文件夹为空", e);
        }

    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    public String getAppBase() {
        return appBase;
    }

    @Override
    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    @Override
    public File getAppBaseFile() {
        if (appBaseFile == null) {
            appBaseFile = new File(getAppBase());
        }
        return appBaseFile;
    }



}
