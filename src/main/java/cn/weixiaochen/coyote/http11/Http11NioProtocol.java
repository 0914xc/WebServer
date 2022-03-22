package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.catalina.connector.Connector;
import cn.weixiaochen.coyote.AbstractProtocol;
import cn.weixiaochen.coyote.Processor;

/**
 * @author 0914xc 2022/3/13
 */
public class Http11NioProtocol extends AbstractProtocol {

    private Connector connector;

    public Http11NioProtocol() {
        super(new NioEndpoint());
        getEndpoint().setProtocolHandler(this);
    }

    @Override
    public Processor createProcessor() {
        Http11Processor processor = new Http11Processor();
        processor.setAdapter(getAdapter());
        return processor;
    }
}
