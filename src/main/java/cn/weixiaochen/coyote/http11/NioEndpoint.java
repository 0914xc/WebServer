package cn.weixiaochen.coyote.http11;

import cn.weixiaochen.coyote.AbstractEndpoint;
import cn.weixiaochen.coyote.AbstractProtocol;
import cn.weixiaochen.coyote.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 0914xc 2022/3/13
 */
public class NioEndpoint extends AbstractEndpoint {

    private final static Logger logger = LoggerFactory.getLogger(NioEndpoint.class);

    private ServerSocket serverSocket;

    @Override
    public void startInternal() throws Exception {
        while(true) {
            Socket accept = this.serverSocket.accept();
            Processor processor = ((AbstractProtocol) getProtocolHandler()).createProcessor();
            processor.service(accept);
        }
    }

    @Override
    public void bind() throws IOException {
        serverSocket = new ServerSocket(getPort());
    }
}
