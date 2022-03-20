package cn.weixiaochen.catalina.startup;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author 0914xc 2022/3/15
 */
public class ContextRuleSet extends RuleSetBase {

    /**
     * The matching pattern prefix to use for recognizing our elements.
     */
    protected final String prefix;

    public ContextRuleSet() {
        this("");
    }

    public ContextRuleSet(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void addRuleInstances(Digester digester) {
        digester.addRule(prefix + "Context",
                new LifecycleListenerRule("cn.weixiaochen.catalina.startup.ContextConfig"));
    }
}
