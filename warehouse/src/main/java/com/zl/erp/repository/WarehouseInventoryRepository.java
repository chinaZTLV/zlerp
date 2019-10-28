package com.zl.erp.repository;

import com.zl.erp.entity.WarehouseInventoryEntity;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description: 仓库存储
 * @Author: zhutao
 * @Date: 2019/9/24
 */
public interface WarehouseInventoryRepository extends BaseRepository<WarehouseInventoryEntity, Integer> {

    /**
     * 获取库存详情信息
     *
     * @param stockId 库存ID
     * @return 库存详情
     */
    WarehouseInventoryEntity getByStockId(Integer stockId);

    /**
     * 根据物料类型Id获取库存信息
     *
     * @param productKindId 料类型Id
     * @return 库存信息
     */
    @Query(value = "SELECT * FROM v_warehouse_inventory WHERE product_kind_id = ?1",nativeQuery = true)
    WarehouseInventoryEntity getByProductKindId(Integer productKindId);
}
