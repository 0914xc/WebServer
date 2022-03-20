package cn.weixiaochen.catalina;

/**
 * @author 0914xc 2021/12/11
 */
public interface Container extends Lifecycle {

    String getName();

    void setName(String name);

    Container getParent();

    void setParent(Container container);

    Container findChild(String name);

    Container[] findChildren();

    void addChild(Container container);

    Pipeline getPipeline();

}
