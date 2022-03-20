package cn.weixiaochen.catalina;

import cn.weixiaochen.catalina.core.StandardPipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 0914xc 2021/12/12
 */
public abstract class ContainerBase extends LifecycleBase implements Container {

    protected String name = null;

    protected Container parent = null;

    protected final Map<String, Container> children = new HashMap<>();

    /**
     * The Pipeline object with which this Container is associated.
     */
    protected final Pipeline pipeline = new StandardPipeline(this);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public void setParent(Container parent) {
        this.parent = parent;
    }

    @Override
    public Container findChild(String name) {
        if (name == null) {
            return null;
        }
        return children.get(name);
    }

    @Override
    public Container[] findChildren() {
        return children.values().toArray(new Container[0]);
    }

    @Override
    public void addChild(Container child) {
        child.setParent(this);
        children.put(child.getName(), child);
    }

    @Override
    public Pipeline getPipeline() {
        return this.pipeline;
    }

    public void addValue(Valve valve) {
        pipeline.addValue(valve);
    }
}
