package cn.weixiaochen.container.core;

import cn.weixiaochen.connector.HttpProcessor;
import cn.weixiaochen.container.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author 0914xc 2022/1/23
 */
public class StandardServer implements Server {

    private static final Logger logger = LoggerFactory.getLogger(StandardServer.class);

    private int port = 8080;

    public void start() {
        try{
            // create a server socket
            ServerSocket serverSocket = new ServerSocket(port);

            // wait for connection
            Socket socket = serverSocket.accept();

            // process the request
            process(socket);
            logger.info("Received a request from {}", socket.getInetAddress());

            // close the socket
            socket.close();
        } catch (Exception e) {
            logger.error("Server start failed.", e);
            System.exit(1);
        }
    }

    private void process(Socket socket) {
        HttpProcessor processor = new HttpProcessor();
        processor.process(socket);
    }
}
