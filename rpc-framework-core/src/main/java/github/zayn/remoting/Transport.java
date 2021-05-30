package github.zayn.remoting;

import github.zayn.model.RpcRequest;

/**
 * @ClassName RpcClient
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/5 下午6:07
 **/
public interface Transport {

    Object sendRpcRequest(RpcRequest rpcRequest);
}
