package cn.weixiaochen.catalina;

/**
 * @author 0914xc 2022/3/14
 */
public interface LifecycleListener {

    /**
     * Acknowledge the occurrence of the specified event.
     *
     * @param event LifecycleEvent that has occurred
     */
    void lifecycleEvent(LifecycleEvent event);
}
