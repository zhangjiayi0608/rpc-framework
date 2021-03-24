package github.zayn.remoting.netty.client;


import github.zayn.model.RpcRequest;
import github.zayn.remoting.RpcClient;
import lombok.extern.slf4j.Slf4j;

/**
 * initialize and close Bootstrap object
 *
 * @author shuang.kou
 * @createTime 2020年05月29日 17:51:00
 */
@Slf4j
public final class NettyRpcClient implements RpcClient {

    @Override
    public Object sendRpcRequest(RpcRequest rpcRequest) {
        return null;
    }
}
