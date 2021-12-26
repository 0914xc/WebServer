package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;

/**
 * @author 魏小宸 2022/1/23
 */
public class StandardEngine extends ContainerBase implements Engine {

    @Override
    protected void initInternal() {

    }

    @Override
    protected void startInternal() {
        for (Container child : findChildren()) {
            child.start();
        }
    }

    @Override
    protected void stopInternal() {

    }

    @Override
    protected void destroyInternal() {

    }

}
