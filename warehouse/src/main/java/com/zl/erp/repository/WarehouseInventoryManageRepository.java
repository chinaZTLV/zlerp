package com.zl.erp.repository;

import com.zl.erp.entity.WarehouseInventoryManageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 仓库
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface WarehouseInventoryManageRepository extends JpaRepository<WarehouseInventoryManageEntity,Integer> {
}
