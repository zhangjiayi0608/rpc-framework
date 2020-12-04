package github.zjy;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import github.zjy.annotation.RpcPackageScan;
import github.zjy.provider.HelloServiceImpl;
import github.zjy.remoting.socket.SocketRpcServer;
import github.zjy.service.HelloService;

/**
 * @ClassName SocketDemoMain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:19
 **/
@RpcPackageScan(basePackage = {"github.zjy"})
public class SocketDemoMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SocketDemoMain.class);
        SocketRpcServer socketRpcServer = (SocketRpcServer) applicationContext.getBean(
                "socketRpcServer");
        HelloService helloService = new HelloServiceImpl();
        socketRpcServer.start();
    }
}
