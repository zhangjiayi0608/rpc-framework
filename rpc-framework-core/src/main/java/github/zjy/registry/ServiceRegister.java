package github.zjy.registry;

import java.net.InetSocketAddress;

/**
 * @ClassName serviceRegister
 * @DESCRIPTION 服务注册
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午6:37
 **/
public interface ServiceRegister {
    /**
     * 注册服务
     * @param serviceName
     * @param inetSocketAddress service address
     */
    void registerService(String serviceName, InetSocketAddress inetSocketAddress);
}
