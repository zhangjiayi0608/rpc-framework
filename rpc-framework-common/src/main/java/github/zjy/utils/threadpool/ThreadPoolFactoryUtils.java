package github.zjy.utils.threadpool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ThreadPoolUtils
 * @DESCRIPTION 统一管理的线程池
 * map("threadName",threadpool)
 * @Author zhangjiayi07
 * @Date 2020/12/1 下午3:06
 **/
@Slf4j
@NoArgsConstructor
public final class ThreadPoolFactoryUtils {

    /**
     * 通过 threadName 来区分不同线程池（我们可以把相同 threadName 的线程池看作是为同一业务场景服务）。
     * key: threadName
     * value: threadPool
     */
    private static final ConcurrentHashMap<String, ExecutorService> THREAD_POOLS = new ConcurrentHashMap<>();


    public static ThreadFactory createThreadFactory(String threadName, boolean isDaemon) {
        if (threadName != null) {
            ThreadFactory threadFactory =
                    new ThreadFactoryBuilder().setNameFormat(threadName + "-%d").setDaemon(isDaemon).build();
            return threadFactory;

        } else {
            return Executors.defaultThreadFactory();
        }
    }

    public static ExecutorService createThreadPool(ThreadPoolConfig poolConfig, String threadName, boolean isDaemon) {
        ThreadFactory threadFactory = createThreadFactory(threadName, isDaemon);
        return new ThreadPoolExecutor(poolConfig.getCorePoolSize(), poolConfig.getMaxPoolSize(),
                poolConfig.getKeepAliveTime(), poolConfig.getUnit(), poolConfig.getBlockingQueue(),
                threadFactory);
    }

    public static ExecutorService createThreadPoolIfAbsent(ThreadPoolConfig customThreadPoolConfig, String threadName, Boolean isDaemon) {
        ExecutorService executorService = THREAD_POOLS.computeIfAbsent(threadName, k -> createThreadPool(customThreadPoolConfig,
                threadName, isDaemon));
        //如果被shutdown的话，就移除重建
        if (executorService.isShutdown() || executorService.isTerminated()) {
            THREAD_POOLS.remove(threadName);
            ExecutorService threadPool = createThreadPool(customThreadPoolConfig, threadName, isDaemon);
            THREAD_POOLS.put(threadName, threadPool);
        }
        return executorService;
    }
}
