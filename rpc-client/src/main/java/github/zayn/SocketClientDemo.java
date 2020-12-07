package github.zayn;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import github.zayn.annotation.RpcPackageScan;
import github.zayn.client.HelloClient;

/**
 * @ClassName SocketClientDemo
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/7 下午3:24
 **/
@RpcPackageScan(basePackage = "github.zayn")
public class SocketClientDemo {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SocketClientDemo.class);
        HelloClient helloClient = (HelloClient) applicationContext.getBean("helloClient");
        helloClient.test();
    }
}
