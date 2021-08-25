package github.zayn.remoting.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import github.zayn.entity.RpcServiceParam;
import github.zayn.exception.RpcException;
import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcRequest;
import github.zayn.registry.ServiceDiscover;
import github.zayn.registry.impl.ServiceDiscoverImpl;
import github.zayn.remoting.Transport;
import github.zayn.utils.threadpool.ThreadPoolFactoryUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName SocketRpcClient
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:24
 **/
@Slf4j
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
        try (Socket socket = new Socket()) {
            socket.connect(inetSocketAddress);
            return threadPool.submit(new SocketRpcResponseHandlerCallable(socket, rpcRequest)).get();
        } catch (IOException | ExecutionException e) {
            throw new RpcException("调用服务失败:", e);
        } catch (InterruptedException e) {
            log.info("调用服务失败:InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
