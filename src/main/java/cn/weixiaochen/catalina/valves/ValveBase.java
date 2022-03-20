package cn.weixiaochen.catalina.valves;

import cn.weixiaochen.catalina.Contained;
import cn.weixiaochen.catalina.Container;
import cn.weixiaochen.catalina.Valve;

/**
 * @author 0914xc 2022/3/6
 */
public abstract class ValveBase implements Contained, Valve {

    /**
     * The Container whose pipeline this Valve is a component of.
     */
    protected Container container = null;

    /**
     * The next Valve in the pipeline this Valve is a component of.
     */
    private Valve next = null;

    @Override
    public Container getContainer() {
        return container;
    }

    @Override
    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }
}
