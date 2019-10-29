package com.zl.erp.repository;

import com.zl.erp.entity.ConsumerManageRecordEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 客户管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface ConsumerManageRepository extends BaseRepository<ConsumerManageRecordEntity, Integer> {

    /**
     * 查询当前用户名称是否存在
     *
     * @param consumerName 客户名称
     * @return 用户信息
     */
    ConsumerManageRecordEntity getByConsumerName(String consumerName);

    /**
     * 检查是否存在
     *
     * @param consumerName 客户名称
     * @return count
     */
    @Query(value = "SELECT COUNT(1) FROM consumer_manage_record WHERE consumer_name = ?1 ", nativeQuery = true)
    Integer checkExists(String consumerName);

    /**
     * 检查是否存在
     *
     * @param consumerName 客户名称
     * @param consumerId   客户编号
     * @return count
     */
    @Query(value = "SELECT COUNT(1) FROM consumer_manage_record WHERE consumer_name = ?1 AND consumer_id != ?2", nativeQuery = true)
    Integer checkExists(String consumerName, Integer consumerId);

    /**
     * 获取客户编号
     *
     * @param consumerId 客户编号
     * @return 客户
     */
    ConsumerManageRecordEntity getByConsumerId(Integer consumerId);

    /**
     * 获取厂方信息
     *
     * @return 厂方信息列表
     */
    @Query(value = "SELECT * FROM consumer_manage_record WHERE consumer_type = 0", nativeQuery = true)
    List<ConsumerManageRecordEntity> getConsumerListByType();

    /**
     * 获取客户信息
     *
     * @return 厂方信息列表
     */
    @Query(value = "SELECT * FROM consumer_manage_record WHERE consumer_type = 1", nativeQuery = true)
    List<ConsumerManageRecordEntity> getConsumerList();
}
