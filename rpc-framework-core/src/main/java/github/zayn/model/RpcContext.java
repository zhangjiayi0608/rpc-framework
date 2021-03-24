package github.zayn.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RpcContext
 * @DESCRIPTION 上下文信息
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午5:10
 **/
public class RpcContext {
    private static final ThreadLocal<Map<String, Object>> CONTEXT = new ThreadLocal<>();

    public static void put(String key, Object value) {
        CONTEXT.get().put(key, value);
    }

    public static void clear() {
        CONTEXT.remove();
    }

    public static void init() {
        CONTEXT.set(new HashMap<>());
    }

    public static void setTraceId(String traceId) {
        CONTEXT.get().put("traceId", traceId);
    }

    public static String traceId() {
        return (String) CONTEXT.get().get("traceId");
    }


}
