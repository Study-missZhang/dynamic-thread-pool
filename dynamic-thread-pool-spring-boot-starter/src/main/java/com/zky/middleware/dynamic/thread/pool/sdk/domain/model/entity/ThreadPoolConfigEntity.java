package com.zky.middleware.dynamic.thread.pool.sdk.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhangKaiYuan
 * @description: 线程池配置实体对象
 * @create: 2025/3/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreadPoolConfigEntity {

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 线程池名称
     */
    private String threadPoolName;

    /**
     * 核心线程数
     */
    private int corePoolSize;

    /**
     * 最大线程数
     */
    private int maximumPoolSize;

    /**
     * 当前活跃线程数
     */
    private int activeCount;

    /**
     * 当前池中线程数
     */
    private int poolSize;

    /**
     * 队列类型
     */
    private String queueType;

    /**
     * 当前队列任务数
     */
    private int queueSize;

    /**
     * 队列剩余任务数
     */
    private int remainingCapacity;

    public ThreadPoolConfigEntity(String appName, String threadPoolName) {
        this.appName = appName;
        this.threadPoolName = threadPoolName;
    }
}
