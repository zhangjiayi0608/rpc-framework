package github.zjy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName RpcServiceParam
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2020/12/3 上午11:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class RpcServiceParam {
    /**
     * serviceName
     */
    private String serviceName;
    /**
     * 版本
     */
    private String version;
    /**
     * 接口有多个实现时的分组
     */
    private String group;

    public String toRpcServiceName() {
        return this.getServiceName() + "_" + this.getGroup() + "_" + this.getVersion();
    }
}
