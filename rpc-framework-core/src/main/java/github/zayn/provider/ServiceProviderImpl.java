package github.zayn.provider;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import github.zayn.entity.RpcServiceParam;
import github.zayn.enums.RemoteTypeEnum;
import github.zayn.factory.SingletonFactory;
import github.zayn.registry.ServiceRegister;
import github.zayn.registry.impl.ServiceRegisterImpl;
import github.zayn.remoting.netty.server.NettyRpcServer;
import github.zayn.remoting.socket.SocketRpcServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
/**
 * @ClassName ServiceProviderImpl
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:31
 **/
public class ServiceProviderImpl implements ServiceProvider {
    /**
     * key:RpcServiceParam (serviceName+group+version)
     * value:service object
     */
    private final Map<String, Object> serviceMap;
    /**
     * RpcServiceParam (serviceName+group+version)
     */
    private final Set<String> registeredServiceSet;
    private final ServiceRegister serviceRegister;
    private static final int DEFAULT_PORT = 9998;

    public ServiceProviderImpl() {
        this.serviceRegister = SingletonFactory.getInstance(ServiceRegisterImpl.class);
        this.serviceMap = new ConcurrentHashMap<>();
        this.registeredServiceSet = ConcurrentHashMap.newKeySet();
    }


    public void publishService(Object service) {
        this.publishService(service, new RpcServiceParam(), RemoteTypeEnum.DEFAULT);
    }

    public void addService(Object service, Class<?> serviceClass, RpcServiceParam param) {
        String serviceName = param.toRpcServiceName();
        if (registeredServiceSet.contains(serviceName)) {
            return;
        }
        registeredServiceSet.add(serviceName);
        serviceMap.put(serviceName, service);
        log.info("add service successfully! service is [{}],interfaces is [{}]", serviceName, service.getClass().getInterfaces());

    }

    public Object getService(RpcServiceParam param) {
        Object service = serviceMap.get(param.toRpcServiceName());
        return service;
    }

    public void publishService(Object service, RpcServiceParam param, RemoteTypeEnum remoteTypeEnum) {
        try {
            //获取本机ip
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            //反射获取当前对象的第一个接口
            //TODO 如果接口多继承怎么办？虽然绝对不会那么搞
            Class<?> firstInterface = service.getClass().getInterfaces()[0];
            String serviceName = firstInterface.getCanonicalName();
            param.setServiceName(serviceName);
            this.addService(service, firstInterface, param);
            Integer port;
            switch (remoteTypeEnum) {
                case SOCKET:
                    port = SocketRpcServer.PORT;
                    break;
                case NETTY:
                    port = NettyRpcServer.PORT;
                    break;
                case DEFAULT:
                    port = DEFAULT_PORT;
                    break;
                default:
                    port = DEFAULT_PORT;
            }
            serviceRegister.registerService(param.toRpcServiceName(), new InetSocketAddress(hostAddress, port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
