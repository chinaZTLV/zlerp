package com.zl.erp.service;

import com.alibaba.fastjson.JSON;
import com.zl.erp.entity.ConsumerManageRecordEntity;
import com.zl.erp.entity.MaterialKindManageEntity;
import com.zl.erp.repository.ConsumerManageRepository;
import com.zl.erp.repository.MaterialKindManageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zl.erp.constants.CommonConstants.*;

/**
 * @Description: 基础缓存
 * @Author: zhutao
 * @Date: 2019/9/23
 */
@Slf4j
@Service
public class BaseCacheService {

    private final RedisService redisService;

    private final ConsumerManageRepository consumerRepository;

    private final MaterialKindManageRepository kindManageRepository;

    @Autowired
    public BaseCacheService(RedisService redisService, ConsumerManageRepository consumerRepository, MaterialKindManageRepository kindManageRepository) {
        this.redisService = redisService;
        this.consumerRepository = consumerRepository;
        this.kindManageRepository = kindManageRepository;
    }

    /**
     * 刷新缓存
     *
     * @param cacheType 缓存类型
     * @return 是否执行成功
     */
    void refreshBaseCache(String cacheType) {
        if (REDIS_CACHE_CONSUMER_KEY.equals(cacheType)) {
            List<ConsumerManageRecordEntity> consumerManageRecordList = consumerRepository.getConsumerListByType();
            log.warn("[刷新用户缓存]查询用户信息：{}", JSON.toJSONString(consumerManageRecordList));
            consumerManageRecordList.forEach(consumer -> {
                if (FACTORY_TYPE.equals(consumer.getConsumerType())) {
                    redisService.hset(REDIS_CACHE_CONSUMER_KEY, String.valueOf(consumer.getConsumerId()), consumer.getConsumerName());
                }
            });
        } else if (REDIS_CACHE_KIND_KEY.equals(cacheType) || REDIS_CACHE_KIND_INFO_KEY.equals(cacheType)) {
            List<MaterialKindManageEntity> materialKindManageList = kindManageRepository.queryMaterialKindManageList();
            materialKindManageList.forEach(kindManage -> {
                redisService.hset(REDIS_CACHE_KIND_KEY, String.valueOf(kindManage.getProductKindId()), kindManage.getProductKindName());
                redisService.hset(REDIS_CACHE_KIND_INFO_KEY, String.valueOf(kindManage.getProductKindId()), JSON.toJSONString(kindManage));
            });
        }
    }
}
