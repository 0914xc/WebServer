package cn.weixiaochen.catalina;

import java.io.File;

/**
 * @author 0914xc 2022/1/23
 */
public interface Host extends Container{

    File getAppBaseFile();

    void setAppBase(String appBase);

}
