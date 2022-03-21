package cn.weixiaochen.catalina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author 0914xc 2022/1/24
 */
public abstract class LifecycleBase implements Lifecycle {

    private static final Logger logger = LoggerFactory.getLogger(LifecycleBase.class);

    private final List<LifecycleListener> lifecycleListeners = new CopyOnWriteArrayList<>();

    /**
     * The current state of the source component.
     */
    private volatile LifecycleState state = LifecycleState.NEW;

    @Override
    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.add(listener);
    }

    @Override
    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleListeners.toArray(new LifecycleListener[0]);
    }

    @Override
    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleListeners.remove(listener);
    }

    /**
     * Allow sub classes to fire {@link Lifecycle} events.
     *
     * @param type  Event type
     * @param data  Data associated with event.
     */
    protected void fireLifecycleEvent(String type, Object data) {
        LifecycleEvent event = new LifecycleEvent(this, type, data);
        for (LifecycleListener listener : lifecycleListeners) {
            listener.lifecycleEvent(event);
        }
    }

    @Override
    public final synchronized void init() throws LifecycleException {
        setStateInternal(LifecycleState.INITIALIZING, null, false);
        initInternal();
        setStateInternal(LifecycleState.INITIALIZED, null, false);
    }

    @Override
    public final synchronized void start() throws LifecycleException {
        setStateInternal(LifecycleState.STARTING_PREP, null, false);
        startInternal();
        setStateInternal(LifecycleState.STARTED, null, false);
    }

    @Override
    public final synchronized void stop() throws LifecycleException {
        setStateInternal(LifecycleState.STOPPING_PREP, null, false);
        stopInternal();
        setStateInternal(LifecycleState.STOPPED, null, false);
    }

    @Override
    public final synchronized void destroy() throws LifecycleException {
        setStateInternal(LifecycleState.DESTROYING, null, false);
        destroyInternal();
        setStateInternal(LifecycleState.DESTROYED, null, false);
    }

    protected abstract void initInternal() throws LifecycleException;

    protected abstract void startInternal() throws LifecycleException;

    protected abstract void stopInternal() throws LifecycleException;

    protected abstract void destroyInternal() throws LifecycleException;

    protected void setState(LifecycleState state) throws LifecycleException {
        setStateInternal(state, null, true);
    }

    @Override
    public LifecycleState getState() {
        return state;
    }

    private void setStateInternal(LifecycleState state, Object data, boolean check) throws LifecycleException {

        if (check) {
            if (state == null) {
                invalidTransition("null");
                return;
            }
        }

        this.state = state;
        String lifecycleEvent = state.getLifecycleEvent();
        if (lifecycleEvent != null) {
            fireLifecycleEvent(lifecycleEvent, data);
        }
    }

    private void invalidTransition(String type) throws LifecycleException {
        logger.error("An invalid Lifecycle transition was attempted {} for component {} in state {}", type, toString(), state);
        throw new LifecycleException();
    }

}
