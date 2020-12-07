package github.zayn.registry.impl;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Random;

import org.apache.curator.framework.CuratorFramework;

import github.zayn.registry.ServiceDiscover;
import github.zayn.utils.zookeeper.CuratorUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ServiceRegisterImpl
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午6:38
 **/
@Slf4j
public class ServiceDiscoverImpl implements ServiceDiscover {
    public InetSocketAddress discoverService(String serviceName) {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        List<String> serviceUrlList = CuratorUtils.getChildNodeList(zkClient, serviceName);
        //TODO 后期负载均衡  现在的逻辑是随机打到不同的IP地址中
        Random random = new Random();
        int nextInt = random.nextInt(serviceUrlList.size());
        String serviceUrl = serviceUrlList.get(nextInt);
        log.info("service address:[{}]", serviceUrl);
        String[] socketAddressArray = serviceUrl.split(":");
        String host = socketAddressArray[0];
        int port = Integer.parseInt(socketAddressArray[1]);
        return new InetSocketAddress(host, port);
    }
}
