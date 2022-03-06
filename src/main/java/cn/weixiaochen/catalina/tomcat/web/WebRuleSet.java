package cn.weixiaochen.catalina.tomcat.web;

import cn.weixiaochen.catalina.Context;
import cn.weixiaochen.catalina.Wrapper;
import org.apache.commons.digester.Digester;
import org.apache.commons.digester.Rule;
import org.apache.commons.digester.RuleSetBase;
import org.xml.sax.Attributes;

public class WebRuleSet extends RuleSetBase {

    protected String prefix;

    public WebRuleSet() {
        this("web-app/");
    }

    public WebRuleSet(String prefix) {
        super();
        this.prefix = prefix;
    }

    @Override
    public void addRuleInstances(Digester digester) {
        digester.addRule(prefix + "servlet", new WrapperCreateRule());
        digester.addSetNext(prefix + "servlet", "addChild", "cn.weixiaochen.catalina.Container");

        digester.addCallMethod(prefix + "servlet/servlet-name", "setName", 0);
        digester.addCallMethod(prefix + "servlet/servlet-class", "setServletClass", 0);

        digester.addCallMethod(prefix + "servlet-mapping", "addServletMapping", 2);
        digester.addCallParam(prefix + "servlet-mapping/servlet-name", 1);
        digester.addCallParam(prefix + "servlet-mapping/url-pattern", 0);
    }

    static final class WrapperCreateRule extends Rule {

        @Override
        public void begin(String namespace, String name, Attributes attributes) throws Exception {
            Context context = (Context) digester.peek(digester.getCount() - 1);
            Wrapper wrapper = context.createWrapper();
            digester.push(wrapper);
        }

        @Override
        public void end(String namespace, String name) throws Exception {
            digester.pop();
        }
    }
}
