package cn.weixiaochen.catalina.core;

import cn.weixiaochen.catalina.*;

/**
 * @author 0914xc 2021/12/12
 */
public class StandardPipeline extends LifecycleBase implements Pipeline {

    /**
     * The basic Valve (if any) associated with this Pipeline.
     */
    protected Valve basic = null;

    /**
     * The Container with which this Pipeline is associated.
     */
    protected Container container = null;

    /**
     * The first valve associated with this Pipeline.
     */
    protected Valve first = null;

    public StandardPipeline() {
        this(null);
    }

    public StandardPipeline(Container container) {
        setContainer(container);
    }

    @Override
    protected void initInternal() throws LifecycleException {

    }

    @Override
    protected void startInternal() throws LifecycleException {

    }

    @Override
    protected void stopInternal() throws LifecycleException {

    }

    @Override
    protected void destroyInternal() throws LifecycleException {

    }

    public Container getContainer() {
        return this.container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    @Override
    public Valve getBasic() {
        return this.basic;
    }

    @Override
    public void setBasic(Valve valve) {
        if (valve == null) {
            return;
        }
        if (valve instanceof Contained) {
            ((Contained) valve).setContainer(this.container);
        }

        this.basic = valve;
    }

    @Override
    public void addValue(Valve valve) {

        // Validate that we can add this Valve
        if (valve instanceof Contained) {
            ((Contained) valve).setContainer(this.container);
        }

        if (first == null) {
            first = valve;
            valve.setNext(basic);
        }
    }

    @Override
    public void removeValue(Valve valve) {

    }

    @Override
    public Valve[] getValues() {
        return new Valve[0];
    }

    @Override
    public Valve getFirst() {
        return first;
    }
}
