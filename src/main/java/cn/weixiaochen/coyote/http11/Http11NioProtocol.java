package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.catalina.connector.Connector;
import cn.weixiaochen.coyote.AbstractProtocol;
import cn.weixiaochen.coyote.Adapter;
import cn.weixiaochen.coyote.Processor;

/**
 * @author 0914xc 2022/3/13
 */
public class Http11NioProtocol extends AbstractProtocol {

    private Connector connector;

    public Http11NioProtocol() {
        super(new NioEndpoint());
    }

    @Override
    public Adapter getAdapter() {
        return null;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Processor createProcessor() {
        Http11Processor processor = new Http11Processor();
        processor.setAdapter(adapter);
        return processor;
    }
}
