package com.zl.erp.repository;

import com.zl.erp.entity.PurchaseSellingOrderRecordEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description: 订单管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface PurchaseSellingOrderRepository extends BaseRepository<PurchaseSellingOrderRecordEntity, Integer> {

    /**
     * 获取订单信息
     *
     * @param orderId 订单ID
     * @return 响应信息
     */
    PurchaseSellingOrderRecordEntity getByOrderId(Integer orderId);

    /**
     * 更新订单信息
     *
     * @param tradeType 交易状态
     * @param orderId   订单ID
     */
    @Modifying
    @Query(value = "UPDATE purchase_selling_order_record SET trade_type = ?1 WHERE order_id = ?2", nativeQuery = true)
    void updateOrderInfoByOrderId(String tradeType, Integer orderId);

    /**
     * 查询数量
     *
     * @param consumerId 客户ID
     * @param manageType 管理类型
     * @return Integer
     */
    @Query(value = "SELECT SUM(stock_num) from purchase_selling_order_record WHERE consumer_id = ?1 AND manage_type = ?2 AND trade_type=2", nativeQuery = true)
    Integer getTotalStockCount(Integer consumerId, Integer manageType);

}
