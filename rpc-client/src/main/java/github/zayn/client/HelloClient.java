package github.zayn.client;

import org.springframework.stereotype.Component;

import github.zayn.annotation.RpcReference;
import github.zayn.model.Hello;
import github.zayn.service.HelloService;

/**
 * @ClassName HelloClient
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/7 下午3:14
 **/
@Component
public class HelloClient {

    @RpcReference(version = "version1", group = "group1")
    private HelloService helloService;

    private static final int DEFAULT_SLEEP_TIME = 100;

    public void test() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            System.out.println(helloService.sayHello(new Hello("hello world!" + i, "zjy say hello world to you!" + i)));
            Thread.sleep(DEFAULT_SLEEP_TIME);
        }
    }
}
