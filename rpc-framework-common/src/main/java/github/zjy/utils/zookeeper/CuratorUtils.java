package github.zjy.utils.zookeeper;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import github.zjy.utils.PropertiesFileUtil;

/**
 * @ClassName CuratorUtils
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/3 下午4:50
 **/
public class CuratorUtils {
    private static final Properties ZK_PROPERTY = PropertiesFileUtil.readPropertiesFile("zk.properties");
    private static final int SLEEP_TIME = Integer.valueOf(ZK_PROPERTY.getProperty("zk.sleep.time"));
    private static final int MAX_RETRIES = Integer.valueOf(ZK_PROPERTY.getProperty("zk.retry"));
    public static final String ZK_REGISTER_ROOT_PATH = ZK_PROPERTY.getProperty("zk.register.root.path");
    private static final Map<String, List<String>> SERVICE_ADDRESS_MAP = new ConcurrentHashMap<>();
    private static final Set<String> REGISTERED_PATH_SET = ConcurrentHashMap.newKeySet();
    private static CuratorFramework zkClient;

    private CuratorUtils() {
    }

    /**
     * 建立zk连接，如果没有就创建
     * @return
     */
    public static CuratorFramework getZkClient() {
        Properties properties = PropertiesFileUtil.readPropertiesFile("rpc.properties");
        String rpcZkAddress = properties.getProperty("rpc.zookeeper.address");
        if (zkClient != null && zkClient.getState() == CuratorFrameworkState.STARTED) {
            return zkClient;
        }
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(SLEEP_TIME, MAX_RETRIES);
        zkClient = CuratorFrameworkFactory.builder()
                .connectString(rpcZkAddress)
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        return zkClient;
    }
}
