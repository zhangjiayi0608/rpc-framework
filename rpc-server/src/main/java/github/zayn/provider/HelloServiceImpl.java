package github.zayn.provider;

import github.zayn.annotation.RpcService;
import github.zayn.model.Hello;
import github.zayn.model.RpcContext;
import github.zayn.service.HelloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName HelloServiceImpl
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午3:10
 **/
@Slf4j
@RpcService(group = "group1", version = "version1")
public class HelloServiceImpl implements HelloService {

    //test代码块，用来看Bean是否被创建
    static {
        log.info("HelloServiceImpl已经被创建！");
    }

    @Override
    public String sayHello(Hello hello) {
        log.info("HelloServiceImpl收到客户端message: {}.", hello.getMessage());
        System.out.println("HelloServiceImpl收到客户端message: [" + hello.getMessage() + "]");
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        System.out.println("HelloServiceImpl返回:[" + result + "]");
        String s = RpcContext.traceId();
        System.out.println(s);
        return result;
    }
}
