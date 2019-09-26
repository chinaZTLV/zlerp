package com.zl.erp.repository;

import com.zl.erp.entity.WarehouseOrderEntity;

/**
 * @Description: 订单
 * @Author: zhutao
 * @Date: 2019/9/26
 */
public interface WarehouseOrderRepository extends BaseRepository<WarehouseOrderEntity, Integer> {

    /**
     * 根据订单ID查询订单信息
     *
     * @param orderId 订单ID
     * @return 订单
     */
    WarehouseOrderEntity getByOrderId(Integer orderId);
}
