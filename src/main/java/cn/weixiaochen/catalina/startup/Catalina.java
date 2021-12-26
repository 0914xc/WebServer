package cn.weixiaochen.catalina.startup;

import cn.weixiaochen.catalina.Server;
import org.apache.commons.digester.Digester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

/**
 * @author 魏小宸 2022/1/23
 */
public class Catalina {

    private final static Logger logger = LoggerFactory.getLogger(Catalina.class);

    protected String configFile = "conf/server.xml";

    protected Server server = null;

    public void load() {
        try {
            parseServerXml();
        } catch (IOException | SAXException e) {
            logger.info("解析server.xml失败！", e);
        }
        getServer().init();
    }

    public void start() {
        getServer().start();
    }

    protected void parseServerXml() throws IOException, SAXException {
        Digester digester = createDigester();
        // 将catalina作为栈顶元素
        digester.push(this);

        digester.parse(configFile());

    }

    protected Digester createDigester() {
        // 创建Digester解析器
        Digester digester = new Digester();

        // 不使用DTD规则对Xml文件进行校验
        digester.setValidating(false);

        // 设置解析规则
        digester.addObjectCreate("Server", "cn.weixiaochen.catalina.core.StandardServer");
        digester.addSetProperties("Server");
        digester.addSetNext("Server", "setServer", "cn.weixiaochen.catalina.Server");

        digester.addObjectCreate("Server/Service", "cn.weixiaochen.catalina.core.StandardService");
        digester.addSetProperties("Server/Service");
        digester.addSetNext("Server/Service", "addService", "cn.weixiaochen.catalina.Service");

        digester.addObjectCreate("Server/Service/Connector", "cn.weixiaochen.catalina.connector.Connector");
        digester.addSetProperties("Server/Service/Connector");
        digester.addSetNext("Server/Service/Connector", "addConnector", "cn.weixiaochen.catalina.connector.Connector");

        digester.addObjectCreate("Server/Service/Engine", "cn.weixiaochen.catalina.core.StandardEngine");
        digester.addSetProperties("Server/Service/Engine");
        digester.addSetNext("Server/Service/Engine", "setContainer", "cn.weixiaochen.catalina.Engine");

        digester.addObjectCreate("Server/Service/Engine/Host", "cn.weixiaochen.catalina.core.StandardHost");
        digester.addSetProperties("Server/Service/Engine/Host");
        digester.addSetNext("Server/Service/Engine/Host", "addChild", "cn.weixiaochen.catalina.Container");

        return digester;
    }

    private File configFile() {
        String catalinaBase = this.getClass().getResource("/").getPath()
                .replace("/target/classes", "");
        return new File(catalinaBase, configFile);
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
