package github.zayn.remoting.netty.server;

import github.zayn.factory.SingletonFactory;
import github.zayn.provider.ServiceProvider;
import github.zayn.provider.ServiceProviderImpl;

/**
 * @ClassName SocketRpcServer
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:24
 **/
public class NettyRpcServer {
    public static final int PORT = 9999;

    private final ServiceProvider serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);

    public void start() {
        return;
    }
}
