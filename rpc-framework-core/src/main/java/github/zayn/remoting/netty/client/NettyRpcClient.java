package github.zayn.remoting.netty.client;


import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;

import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcRequest;
import github.zayn.model.RpcResponse;
import github.zayn.registry.ServiceDiscover;
import github.zayn.remoting.Transport;
import lombok.extern.slf4j.Slf4j;

/**
 * initialize and close Bootstrap object
 *
 * @author shuang.kou
 * @createTime 2020年05月29日 17:51:00
 */
@Slf4j
public final class NettyRpcClient implements Transport {

    private final ServiceDiscover serviceDiscover;

    public NettyRpcClient() {
        this.serviceDiscover = SingletonFactory.getInstance(ServiceDiscover.class);
    }

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        CompletableFuture<RpcResponse<Object>> responseFuture = new CompletableFuture<>();
        String serviceName = rpcRequest.toRpcParams().getServiceName();
        InetSocketAddress inetSocketAddress = serviceDiscover.discoverService(serviceName);

        return null;
    }
}
