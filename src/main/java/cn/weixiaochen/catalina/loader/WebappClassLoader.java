package cn.weixiaochen.catalina.loader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author 0914xc 2022/1/24
 */
public class WebappClassLoader extends URLClassLoader {
    private static final String CLASS_FILE_SUFFIX  = ".class";

    public WebappClassLoader(URL[] urls) {
        super(urls);
    }
}
