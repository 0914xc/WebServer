package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.LifecycleEvent;
import cn.weixiaochen.catalina.LifecycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 0914xc 2022/3/14
 */
public class ContextConfig implements LifecycleListener {

    private final static Logger logger = LoggerFactory.getLogger(ContextConfig.class);

    @Override
    public void lifecycleEvent(LifecycleEvent event) {
        logger.info("context config.");
    }
}
