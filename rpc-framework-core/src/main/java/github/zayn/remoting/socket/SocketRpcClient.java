package github.zayn.remoting.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import github.zayn.entity.RpcServiceParam;
import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcRequest;
import github.zayn.registry.ServiceDiscover;
import github.zayn.registry.impl.ServiceDiscoverImpl;
import github.zayn.remoting.Transport;

/**
 * @ClassName SocketRpcClient
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:24
 **/
public class SocketRpcClient implements Transport {
    private final ServiceDiscover serviceDiscover;

    public SocketRpcClient() {
        this.serviceDiscover = SingletonFactory.getInstance(ServiceDiscoverImpl.class);
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        RpcServiceParam param =
                RpcServiceParam.builder()
                        .serviceName(rpcRequest.getInterfaceName())
                        .group(rpcRequest.getGroup())
                        .version(rpcRequest.getVersion())
                        .build();
        String serviceName = param.toRpcServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscover.discoverService(serviceName);
        Socket socket = new Socket();
        try {
            socket.connect(inetSocketAddress);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            // Send data to the server through the output stream
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // Read RpcResponse from the input stream
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
