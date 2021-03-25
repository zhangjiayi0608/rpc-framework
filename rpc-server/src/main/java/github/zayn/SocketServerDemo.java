package github.zayn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import github.zayn.annotation.RpcPackageScan;
import github.zayn.remoting.socket.SocketRpcServer;

/**
 * @ClassName SocketDemoMain
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:19
 **/
@RpcPackageScan(basePackage = {"github.zayn"})
public class SocketServerDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SocketServerDemo.class);
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        socketRpcServer.start();

    }
}
