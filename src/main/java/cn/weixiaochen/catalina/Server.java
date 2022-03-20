package cn.weixiaochen.catalina;

/**
 * @author  2022/1/23
 */
public interface Server extends Lifecycle {

    void addService(Service service);
}
