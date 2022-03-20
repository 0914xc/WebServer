package cn.weixiaochen.catalina;

/**
 *
 * @author 0914xc 2021/12/11
 */
public interface Wrapper extends Container {


    /**
     * @return the fully qualified servlet class name for this servlet.
     */
    String getServletClass();

    /**
     * Set the fully qualified servlet class name for this servlet.
     *
     * @param servletClass Servlet class name
     */
    void setServletClass(String servletClass);

    /**
     * Add a mapping associated with the Wrapper.
     *
     * @param mapping The new wrapper mapping
     */
    void addMapping(String mapping);

    /**
     * Remove a mapping associated with the wrapper.
     *
     * @param mapping The pattern to remove
     */
    void removeMapping(String mapping);
}
