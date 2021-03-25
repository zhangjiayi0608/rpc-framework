package github.zayn.remoting.socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import github.zayn.model.RpcContext;
import github.zayn.utils.threadpool.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SocketRpcServer
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:24
 **/
@Slf4j
public class SocketRpcServer {
    public static final int PORT = 9998;
    private final ExecutorService threadPool;

    public SocketRpcServer() {
        threadPool = ThreadPoolFactoryUtils.createThreadPoolIfAbsent("socket-server-rpc-pool");
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket();
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                log.info("client connected [{}]", socket.getInetAddress());
                threadPool.execute(new SocketRpcRequestHandlerRunnable(socket));
                RpcContext.clear();
            }
            threadPool.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            RpcContext.clear();
        }
    }

}
