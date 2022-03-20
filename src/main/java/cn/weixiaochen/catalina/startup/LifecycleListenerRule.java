package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.Container;
import cn.weixiaochen.catalina.LifecycleListener;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * @author 0914xc 2022/3/15
 */
public class LifecycleListenerRule extends Rule {

    /**
     * The name of the <code>LifecycleListener</code> implementation class.
     */
    private final String listenerClass;

    public LifecycleListenerRule(String listenerClass) {
        this.listenerClass = listenerClass;
    }

    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        Container container = (Container) digester.peek();

        // Instantiate a new LifecycleListener implementation object
        Class<?> clazz = Class.forName(listenerClass);
        LifecycleListener listener = (LifecycleListener) clazz.getConstructor().newInstance();

        // Add this LifecycleListener to our associated component
        container.addLifecycleListener(listener);
    }

}
