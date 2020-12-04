package github.zayn.provider;

import github.zayn.entity.RpcServiceParam;
import github.zayn.enums.RemoteTypeEnum;

/**
 * @ClassName ServiceProvider
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/4 上午10:26
 **/
public interface ServiceProvider {
    /**
     * 暴露服务
     * @param service
     */
    void publishService(Object service);

    void publishService(Object service, RpcServiceParam param, RemoteTypeEnum remoteTypeEnum);


    /**
     * 添加服务
     * @param service
     * @param serviceClass
     * @param param
     */
    void addService(Object service, Class<?> serviceClass, RpcServiceParam param);

    /**
     * 获取服务
     * @param param
     * @return
     */
    Object getService(RpcServiceParam param);

}
