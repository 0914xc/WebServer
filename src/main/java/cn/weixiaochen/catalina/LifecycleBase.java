package cn.weixiaochen.catalina;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 魏小宸 2022/1/24
 */
public abstract class LifecycleBase implements Lifecycle {

    private static final Logger logger = LoggerFactory.getLogger(LifecycleBase.class);

    @Override
    public final synchronized void init() {
        initInternal();
    }

    @Override
    public final synchronized void start() {
        startInternal();
    }

    @Override
    public final synchronized void stop() {
        stopInternal();
    }

    @Override
    public final synchronized void destroy() {
        destroyInternal();
    }

    protected abstract void initInternal();

    protected abstract void startInternal();

    protected abstract void stopInternal();

    protected abstract void destroyInternal();

}
