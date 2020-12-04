package github.zjy.provider;

import github.zjy.annotation.RpcService;
import github.zjy.model.Hello;
import github.zjy.service.HelloService;
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

    static {
        System.out.println("HelloServiceImpl已经被创建！");
        log.info("HelloServiceImpl已经被创建！");
    }

    @Override
    public String sayHello(Hello hello) {
        log.info("HelloServiceImpl收到客户端message: {}.", hello.getMessage());
        String result = "Hello description is " + hello.getDescription();
        log.info("HelloServiceImpl返回: {}.", result);
        return result;
    }
}
