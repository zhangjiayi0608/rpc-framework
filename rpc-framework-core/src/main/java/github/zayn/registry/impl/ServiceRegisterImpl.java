package github.zayn.registry.impl;

import java.net.InetSocketAddress;

import org.apache.curator.framework.CuratorFramework;

import github.zayn.registry.ServiceRegister;
import github.zayn.utils.zookeeper.CuratorUtils;

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
