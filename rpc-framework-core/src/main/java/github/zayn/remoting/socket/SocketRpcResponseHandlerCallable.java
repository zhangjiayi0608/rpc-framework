package github.zayn.remoting.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

import github.zayn.model.RpcRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SocketRpcRequestHandler
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:48
 **/
@Slf4j
public class SocketRpcResponseHandlerCallable implements Callable {
    private final Socket socket;
    private final RpcRequest rpcRequest;


    public SocketRpcResponseHandlerCallable(Socket socket, RpcRequest rpcRequest) {
        this.socket = socket;
        this.rpcRequest = rpcRequest;
    }


    @Override
    public Object call() throws Exception {
        Object object = null;
        log.info("client receive message from server by thread: [{}]", Thread.currentThread().getName());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            object = objectInputStream.readObject();
        } catch (IOException e) {
            log.error("occur exception:", e);
        }
        return object;
    }
}
