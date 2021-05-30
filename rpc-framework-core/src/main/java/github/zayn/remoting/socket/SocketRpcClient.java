package github.zayn.remoting.socket;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import github.zayn.entity.RpcServiceParam;
import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcRequest;
import github.zayn.registry.ServiceDiscover;
import github.zayn.registry.impl.ServiceDiscoverImpl;
import github.zayn.remoting.Transport;
import github.zayn.utils.threadpool.ThreadPoolFactoryUtils;

/**
 * @ClassName SocketRpcClient
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:24
 **/
public class SocketRpcClient implements Transport {
    private final ServiceDiscover serviceDiscover;
    private final ExecutorService threadPool;

    public SocketRpcClient() {
        this.serviceDiscover = SingletonFactory.getInstance(ServiceDiscoverImpl.class);
        threadPool = ThreadPoolFactoryUtils.createThreadPoolIfAbsent("socket-client-rpc-pool");
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
            return threadPool.submit(new SocketRpcResponseHandlerCallable(socket, rpcRequest)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
