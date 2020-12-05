package github.zayn.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

import github.zayn.entity.RpcServiceParam;
import github.zayn.model.RpcRequest;
import github.zayn.remoting.RpcClient;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RpcClientProxy
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/5 下午5:44
 **/
@Slf4j
public class RpcClientProxy implements InvocationHandler {
    private final RpcClient rpcClient;
    private final RpcServiceParam rpcServiceParam;

    public RpcClientProxy(RpcClient rpcClient, RpcServiceParam rpcServiceParam) {
        this.rpcClient = rpcClient;
        this.rpcServiceParam = rpcServiceParam;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("invoke method:[{}]", method.getName());
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .paramTypes(method.getParameterTypes())
                .parameters(method.getTypeParameters())
                .requestId(UUID.randomUUID().toString())
                .group(rpcServiceParam.getGroup())
                .version(rpcServiceParam.getVersion()).build();

        return null;
    }
}
