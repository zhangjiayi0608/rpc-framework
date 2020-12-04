package github.zayn.utils.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;

class CuratorUtilsTest {

    @Test
    void getZkClient() {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        System.out.println(zkClient);
    }
}