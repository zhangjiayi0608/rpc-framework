package github.zjy.utils.zookeeper;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.curator.framework.CuratorFramework;
import org.junit.jupiter.api.Test;

class CuratorUtilsTest {

    @Test
    void getZkClient() {
        CuratorFramework zkClient = CuratorUtils.getZkClient();
        System.out.println(zkClient);
    }
}