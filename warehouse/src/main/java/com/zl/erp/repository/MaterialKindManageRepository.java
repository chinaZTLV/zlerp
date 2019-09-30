package com.zl.erp.repository;

import com.zl.erp.entity.MaterialKindManageEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 物料类型管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public interface MaterialKindManageRepository extends BaseRepository<MaterialKindManageEntity, Integer> {

    /**
     * 查询物料类型列表
     *
     * @return 物料类型列表
     */
    @Query(value = "SELECT * FROM material_kind_manage", nativeQuery = true)
    List<MaterialKindManageEntity> queryMaterialKindManageList();

    /**
     * 查询物料类型详情
     *
     * @param kindId 物料类型编号
     * @return 物料类型
     */
    @Query(value = "SELECT * FROM material_kind_manage WHERE product_kind_id = ?1", nativeQuery = true)
    MaterialKindManageEntity getMaterialKindById(Integer kindId);

    /**
     * 根据名称获取物料类型
     *
     * @param kindName 物料类型名称
     * @return MaterialKindManageEntity
     */
    MaterialKindManageEntity getAllByProductKindName(String kindName);

    /**
     * 获取该用户下是否有物料信息
     *
     * @param consumerId 厂方ID
     * @return 物料信息
     */
    List<MaterialKindManageEntity> getAllByConsumerId(Integer consumerId);
}
