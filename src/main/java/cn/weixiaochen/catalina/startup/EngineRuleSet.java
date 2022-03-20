package cn.weixiaochen.catalina.startup;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSetBase;

/**
 * @author 0914xc 2022/3/15
 */
public class EngineRuleSet extends RuleSetBase {

    /**
     * The matching pattern prefix to use for recognizing our elements.
     */
    protected final String prefix;

    public EngineRuleSet() {
        this("");
    }

    public EngineRuleSet(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void addRuleInstances(Digester digester) {

        digester.addObjectCreate(prefix + "Engine",
                                 "cn.weixiaochen.catalina.core.StandardEngine",
                                 "className");
        digester.addSetProperties(prefix + "Engine");
        digester.addSetNext(prefix + "Engine",
                            "setContainer",
                            "cn.weixiaochen.catalina.Engine");
    }
}
