package github.zayn.remoting.handler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


import github.zayn.factory.SingletonFactory;
import github.zayn.model.RpcRequest;
import github.zayn.provider.ServiceProvider;
import github.zayn.provider.ServiceProviderImpl;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class RpcRequestHandler {
    private final ServiceProvider serviceProvider;

    public RpcRequestHandler() {
        serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }

    public Object handle(RpcRequest rpcRequest) {
        Object service = serviceProvider.getService(rpcRequest.toRpcParams());
        return invokeTargetMethod(rpcRequest, service);
    }

    private Object invokeTargetMethod(RpcRequest rpcRequest, Object service) {
        Object result = null;
        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
            result = method.invoke(service, rpcRequest.getParameters());
            log.info("service:[{}] successful invoke method:[{}]", rpcRequest.getInterfaceName(), rpcRequest.getMethodName());
        } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException e) {
            log.error("invokeTargetMethod is error ", e);
        }
        return result;
    }
}
