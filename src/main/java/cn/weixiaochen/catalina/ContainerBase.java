package cn.weixiaochen.catalina;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 魏小宸 2021/12/12
 */
public abstract class ContainerBase extends LifecycleBase implements Container {

    protected String name = null;

    protected Container parent = null;

    protected final Map<String, Container> children = new HashMap<>();

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
    public void setParent(Container container) {
        this.parent = container;
    }

    @Override
    public Container findChild(String name) {
        return children.get(name);
    }

    @Override
    public Container[] findChildren() {
        return children.values().toArray(new Container[0]);
    }

    @Override
    public void addChild(Container container) {
        container.setParent(this);
        children.put(getName(), container);
    }
}
