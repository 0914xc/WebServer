package cn.weixiaochen.coyote;

import java.net.Socket;

/**
 * Common interface for processors of all protocols.
 *
 * @author 0914xc 2021/11/9
 */
public interface Processor {

    void service(Socket socket);
}
