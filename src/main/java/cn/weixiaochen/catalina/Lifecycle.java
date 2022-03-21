package cn.weixiaochen.catalina;

/**
 * @author 0914xc 2021/12/11
 */
public interface Lifecycle {

    String BEFORE_INIT_EVENT = "before_init";

    String AFTER_INIT_EVENT = "after_init";

    String START_EVENT = "start";

    String BEFORE_START_EVENT = "before_start";

    String AFTER_START_EVENT = "after_start";

    String STOP_EVENT = "stop";

    String BEFORE_STOP_EVENT = "before_stop";

    String AFTER_STOP_EVENT = "after_stop";

    String AFTER_DESTROY_EVENT = "after_destroy";

    String BEFORE_DESTROY_EVENT = "before_destroy";

    String PERIODIC_EVENT = "periodic";

    String CONFIGURE_START_EVENT = "configure_start";

    String CONFIGURE_STOP_EVENT = "configure_stop";

    /**
     * Add a LifecycleEvent listener to this component.
     *
     * @param listener The listener to add
     */
    void addLifecycleListener(LifecycleListener listener);

    /**
     * Get the life cycle listeners associated with this life cycle.
     *
     * @return An array containing the life cycle listeners associated with this
     *         life cycle. If this component has no listeners registered, a
     *         zero-length array is returned.
     */
    LifecycleListener[] findLifecycleListeners();

    /**
     * Remove a LifecycleEvent listener from this component.
     *
     * @param listener The listener to remove
     */
    void removeLifecycleListener(LifecycleListener listener);

    void init() throws LifecycleException;

    void start() throws LifecycleException;

    void stop() throws LifecycleException;

    void destroy() throws LifecycleException;

    LifecycleState getState();
}
