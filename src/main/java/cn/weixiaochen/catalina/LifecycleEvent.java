package cn.weixiaochen.catalina;

import java.util.EventObject;

/**
 * @author 0914xc 2022/3/14
 */
public final class LifecycleEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    String CONFIGURE_START_EVENT = "configure_start";

    /**
     * Construct a new LifecycleEvent with the specified parameters.
     *
     * @param lifecycle Component on which this event occurred
     * @param type Event type (required)
     * @param data Event data (if any)
     */
    public LifecycleEvent(Lifecycle lifecycle, String type, Object data) {
        super(lifecycle);
        this.type = type;
        this.data = data;
    }

    /**
     * The event data associated with this event.
     */
    private final Object data;

    /**
     * The event type this instance represents.
     */
    private final String type;

    /**
     * @return the event data of this event.
     */
    public Object getData() {
        return data;
    }

    /**
     * @return the Lifecycle on which this event occurred.
     */
    public Lifecycle getLifecycle() {
        return (Lifecycle) getSource();
    }

    /**
     * @return the event type of this event.
     */
    public String getType() {
        return this.type;
    }

}
