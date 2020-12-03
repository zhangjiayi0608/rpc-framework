package github.zjy.registry;

import java.net.InetSocketAddress;

/**
 * @ClassName ServiceDiscover
 * @DESCRIPTION 服务发现
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午6:37
 **/
public interface ServiceDiscover {
    /**
     * 服务发现
     * @param serviceName
     * @return service address
     */
    InetSocketAddress discoverService(String serviceName);
}
