package com.zky.middleware.dynamic.thread.pool.sdk.registry;

import com.zky.middleware.dynamic.thread.pool.sdk.domain.model.entity.ThreadPoolConfigEntity;

import java.util.List;

/**
 * @author: ZhangKaiYuan
 * @description: 注册中心接口
 * @create: 2025/3/31
 */
public interface IRegistry {

    //注册中心添加多个线程池
    void reportThreadPool(List<ThreadPoolConfigEntity> threadPoolConfigEntities);

    //注册中心添加单个线程池
    void reportThreadPoolConfigParameter(ThreadPoolConfigEntity threadPoolConfigEntity);
}
