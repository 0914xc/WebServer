package cn.weixiaochen.catalina;

/**
 * @author 魏小宸 2021/12/11
 */
public interface Lifecycle {

    void init();

    void start();

    void stop();

    void destroy();
}
