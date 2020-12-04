package github.zayn.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import github.zayn.annotation.RpcService;
import github.zayn.entity.RpcServiceParam;
import github.zayn.enums.RemoteTypeEnum;
import github.zayn.factory.SingletonFactory;
import github.zayn.provider.ServiceProvider;
import github.zayn.provider.ServiceProviderImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName RpcBeanPostProcessor
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 下午2:29
 **/
@Slf4j
@Component
public class RpcBeanPostProcessor implements BeanPostProcessor {

    private final ServiceProvider serviceProvider;

    public RpcBeanPostProcessor() {
        serviceProvider = SingletonFactory.getInstance(ServiceProviderImpl.class);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(RpcService.class)) {
            log.info("[{}] is annotated with  [{}]", bean.getClass().getName(), RpcService.class.getCanonicalName());
            //获取@RpcService中的属性
            RpcService annotation = bean.getClass().getAnnotation(RpcService.class);
            RpcServiceParam rpcServiceParam = RpcServiceParam.builder()
                    .group(annotation.group())
                    .version(annotation.version()).build();
            serviceProvider.publishService(bean, rpcServiceParam, RemoteTypeEnum.SOCKET);
        }
        return bean;
    }
}