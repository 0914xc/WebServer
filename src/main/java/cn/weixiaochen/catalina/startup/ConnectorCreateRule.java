package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.Service;
import cn.weixiaochen.catalina.connector.Connector;
import org.apache.commons.digester.Rule;
import org.xml.sax.Attributes;

/**
 * @author 0914xc 2022/3/14
 */
public class ConnectorCreateRule extends Rule {

    @Override
    public void begin(String namespace, String name, Attributes attributes) throws Exception {
        Connector connector = new Connector(attributes.getValue("protocol"));
        digester.push(connector);
    }

    @Override
    public void end(String namespace, String name) throws Exception {
        digester.pop();
    }
}
