package github.zayn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import github.zayn.entity.RpcServiceParam;
import github.zayn.model.RpcRequest;
import github.zayn.model.RpcResponse;
import github.zayn.remoting.Transport;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RpcClientProxy
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/5 下午5:44
 **/
@Slf4j
public class RpcClientProxy implements InvocationHandler {
    private final Transport rpcClient;
    private final RpcServiceParam rpcServiceParam;

    public RpcClientProxy(Transport rpcClient, RpcServiceParam rpcServiceParam) {
        this.rpcClient = rpcClient;
        this.rpcServiceParam = rpcServiceParam;
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke method:[{}]", method.getName());
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .parameters(args)
                .traceId(UUID.randomUUID().toString())
                .group(rpcServiceParam.getGroup())
                .version(rpcServiceParam.getVersion()).build();
        RpcResponse<Object> response = (RpcResponse<Object>) rpcClient.sendRpcRequest(rpcRequest);
        return response.getData();
    }
}
