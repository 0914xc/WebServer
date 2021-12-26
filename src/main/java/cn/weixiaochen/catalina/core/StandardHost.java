package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;

import java.io.File;


/**
 * @author 魏小宸 2022/1/23
 */
public class StandardHost extends ContainerBase implements Host {

    protected String appBase = null;

    protected File appBaseFile = null;

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        depolyApps();
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

    public void depolyApps() {
        File appBase = getAppBaseFile();
        for (File file : appBase.listFiles()) {
            System.out.println(file.toPath());
        }
    }
}
