package github.zjy.registry.impl;

import java.net.InetSocketAddress;

import org.apache.curator.framework.CuratorFramework;

import github.zjy.registry.ServiceRegister;
import github.zjy.utils.zookeeper.CuratorUtils;

/**
 * @ClassName ServiceRegisterImpl
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午6:38
 **/
public class ServiceRegisterImpl implements ServiceRegister {
    public void registerService(String serviceName, InetSocketAddress inetSocketAddress) {
        String servicePath = CuratorUtils.ZK_REGISTER_ROOT_PATH + "/" + serviceName + inetSocketAddress.toString();
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        CuratorUtils.createEphemeralNode(zkClient, servicePath);
    }
}
