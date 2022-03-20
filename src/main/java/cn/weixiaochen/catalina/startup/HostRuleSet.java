package cn.weixiaochen.catalina.startup;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author 0914xc 2022/3/15
 */
public class HostRuleSet extends RuleSetBase {

    /**
     * The matching pattern prefix to use for recognizing our elements.
     */
    protected final String prefix;

    public HostRuleSet() {
        this("");
    }

    public HostRuleSet(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void addRuleInstances(Digester digester) {
        digester.addObjectCreate(prefix + "Host",
                                 "cn.weixiaochen.catalina.core.StandardHost",
                                 "className");
        digester.addSetProperties(prefix + "Host");
        digester.addRule(prefix + "Host",
                new LifecycleListenerRule("cn.weixiaochen.catalina.startup.HostConfig"));
        digester.addSetNext(prefix + "Host",
                            "addChild",
                            "cn.weixiaochen.catalina.Container");

    }
}
