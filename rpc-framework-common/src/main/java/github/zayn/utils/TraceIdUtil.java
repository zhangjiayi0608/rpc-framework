package github.zayn.utils;

import java.util.UUID;

/**
 * @ClassName TraceIdUtil
 * @DESCRIPTION TODO
 * @Author zhangjiayi07
 * @Date 2021/3/24 下午5:33
 **/
public class TraceIdUtil {
    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();
    public static String getTraceId() {
        if (TRACE_ID.get() == null) {
            String s = UUID.randomUUID().toString();
            setTraceId(s);
        }
        return TRACE_ID.get();
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }
}
