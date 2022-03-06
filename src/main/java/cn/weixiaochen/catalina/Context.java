package cn.weixiaochen.catalina;

/**
 * 表示一个Web应用程序。一个Context可以有多个Wrapper。
 * @author 魏小宸 2021/12/11
 */
public interface Context extends Container {

    /**
     * @return the context path for this web application
     */
    String getPath();

    /**
     * Set the context path for this web application
     * @param path The new context path
     */
    void setPath(String path);


    Wrapper createWrapper();

    /**
     * @return the servlet name mapped by the specified pattern (if any)
     * otherwise return <code>null</code>
     *
     * @param pattern Pattern for which a mapping is requested
     */
    String findServletMapping(String pattern);

    /**
     * @return the patterns of all defined servlet mappings for this
     * Context.  If no mappings are defined, a zero-length array is returned.
     */
    String[] findServletMappings();

    /**
     * Add a new servlet mapping, replacing any existing mapping for
     * the specified pattern.
     *
     * @param pattern URL pattern to be mapped
     * @param name Name of the corresponding servlet to execute
     */
    void addServletMapping(String pattern, String name);

}
