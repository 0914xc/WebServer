package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * @author 魏小宸 2022/1/23
 */
public class StandardHost extends ContainerBase implements Host {

    final static Logger logger = LoggerFactory.getLogger(StandardHost.class);

    protected String appBase = null;

    protected File appBaseFile = null;

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        try {
            depolyApps();
        } catch (Exception e) {
            logger.error("webapps文件夹为空", e);
        }
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

    }

    public String getAppBase() {
        return appBase;
    }

    public void setAppBase(String appBase) {
        this.appBase = appBase;
    }

    public File getAppBaseFile() {
        if (appBaseFile == null) {
            appBaseFile = new File(getAppBase());
        }
        return appBaseFile;
    }

    public void depolyApps() throws Exception {
        File appBase = getAppBaseFile();
        File[] webapps = appBase.listFiles();
        if (webapps == null) {
            throw new Exception("webapps文件夹为空");
        }

        for (File file : webapps) {
            logger.info(file.toString());
            if (file.isDirectory()) {
                Context context = new StandardContext();
                context.setName(file.getName()); // 默认context的名字为web文件夹的名字
                this.addChild(context);
            }
        }
    }
}
