package github.zayn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import github.zayn.annotation.RpcPackageScan;
import github.zayn.provider.HelloServiceImpl;
import github.zayn.remoting.socket.SocketRpcServer;
import github.zayn.service.HelloService;

/**
 * @ClassName SocketDemoMain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:19
 **/
@RpcPackageScan(basePackage = {"github.zayn"})
public class SocketDemoMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SocketDemoMain.class);
        SocketRpcServer socketRpcServer = (SocketRpcServer) applicationContext.getBean(
                "socketRpcServer");
        HelloService helloService = new HelloServiceImpl();
        socketRpcServer.start();
    }
}
