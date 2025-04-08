package com.zky.middleware.dynamic.thread.pool.sdk.trigger.listener;

import com.alibaba.fastjson.JSON;
import com.zky.middleware.dynamic.thread.pool.sdk.domain.IDynamicThreadPoolService;
import com.zky.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;
import com.zky.middleware.dynamic.thread.pool.sdk.registry.IRegistry;
import org.redisson.api.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author: ZhangKaiYuan
 * @description: 监听动态线程池变更
 * @create: 2025/4/1
 */
public class ThreadPoolConfigAdjustListener implements MessageListener<ThreadPoolConfigEntity> {

    private final Logger logger = LoggerFactory.getLogger(ThreadPoolConfigAdjustListener.class);

    private final IDynamicThreadPoolService dynamicThreadPoolService;
    private final IRegistry registry;

    public ThreadPoolConfigAdjustListener(IDynamicThreadPoolService dynamicThreadPoolService, IRegistry registry) {
        this.dynamicThreadPoolService = dynamicThreadPoolService;
        this.registry = registry;
    }

    /**
     * 当接收到消息时，会被调用
     * @param charSequence 消息内容
     * @param threadPoolConfigEntity 线程池实体对象
     */
    @Override
    public void onMessage(CharSequence charSequence, ThreadPoolConfigEntity threadPoolConfigEntity) {
        logger.info("动态线程池，调整线程池配置。线程池名称:{} 核心线程数:{} 最大线程数:{}", threadPoolConfigEntity.getThreadPoolName(), threadPoolConfigEntity.getPoolSize(), threadPoolConfigEntity.getMaximumPoolSize());
        dynamicThreadPoolService.updateThreadPoolConfig(threadPoolConfigEntity);

        // 更新后上报最新数据
        List<ThreadPoolConfigEntity> threadPoolConfigEntities = dynamicThreadPoolService.queryThreadPoolList();
        registry.reportThreadPool(threadPoolConfigEntities);

        ThreadPoolConfigEntity threadPoolConfigEntityCurrent = dynamicThreadPoolService.queryThreadPoolConfigByName(threadPoolConfigEntity.getThreadPoolName());
        registry.reportThreadPoolConfigParameter(threadPoolConfigEntityCurrent);
        logger.info("动态线程池，上报线程池配置：{}", JSON.toJSONString(threadPoolConfigEntity));
    }
}
