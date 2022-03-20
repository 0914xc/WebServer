package cn.weixiaochen.coyote;

import cn.weixiaochen.coyote.http11.Http11NioProtocol;

/**
 * @author 0914xc 2022/3/13
 */
public interface ProtocolHandler {

    /**
     * Return the adapter associated with the protocol handler.
     * @return the adapter
     */
    Adapter getAdapter();

    /**
     * The adapter, used to call the connector.
     *
     * @param adapter The adapter to associate
     */
    void setAdapter(Adapter adapter);

    /**
     * Init the protocol.
     */
    void init() throws Exception;


    /**
     * Start the protocol.
     */
    void start() throws Exception;

    static ProtocolHandler create(String protocol) {
        if ("HTTP/1.1".equals(protocol)) {
            return new Http11NioProtocol();
        }
        // TODO other protocol
        return new Http11NioProtocol();
    }

}
