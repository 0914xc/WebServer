package cn.weixiaochen.catalina;

/**
 * Decoupling interface which specifies that an implementing class is
 * associated with at most one <strong>Container</strong> instance.
 *
 * @author 0914xc 2022/3/6
 */
public interface Contained {

    /**
     * Get the {@link Container} with which this instance is associated.
     *
     * @return The Container with which this instance is associated or
     *         <code>null</code> if not associated with a Container
     */
    Container getContainer();


    /**
     * Set the <code>Container</code> with which this instance is associated.
     *
     * @param container The Container instance with which this instance is to
     *  be associated, or <code>null</code> to disassociate this instance
     *  from any Container
     */
    void setContainer(Container container);
}
