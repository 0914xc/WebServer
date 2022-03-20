package cn.weixiaochen.catalina;

/**
 * @author 0914xc 2022/1/23
 */
public interface Engine extends Container {

    /**
     * @return the <code>Service</code> with which we are associated (if any).
     */
    Service getService();

    /**
     * Set the <code>Service</code> with which we are associated (if any).
     *
     * @param service The service that owns this Engine
     */
    void setService(Service service);
}
