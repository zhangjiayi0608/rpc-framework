package github.zayn.remoting.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcContext;
import github.zayn.model.RpcRequest;
import github.zayn.model.RpcResponse;
import github.zayn.remoting.handler.RpcRequestHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SocketRpcRequestHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:48
 **/
@Slf4j
public class SocketRpcRequestHandlerRunnable implements Runnable {
    private final Socket socket;
    private final RpcRequestHandler rpcRequestHandler;


    public SocketRpcRequestHandlerRunnable(Socket socket) {
        this.socket = socket;
        this.rpcRequestHandler = SingletonFactory.getInstance(RpcRequestHandler.class);
    }

    @Override
    public void run() {
        log.info("server handle message from client by thread: [{}]", Thread.currentThread().getName());
        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = rpcRequestHandler.handle(rpcRequest);
            RpcContext.init();
            RpcContext.setTraceId(rpcRequest.getTraceId());
            objectOutputStream.writeObject(RpcResponse.success(result, rpcRequest.getTraceId()));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            log.error("occur exception:", e);
        }
    }
}
