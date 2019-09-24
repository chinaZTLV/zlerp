package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.entity.WarehouseInventoryEntity;
import com.zl.erp.repository.WarehouseInventoryManageRepository;
import com.zl.erp.repository.WarehouseInventoryRepository;
import com.zl.erp.utils.CodeHelper;
import com.zl.erp.utils.CommonDataUtils;
import com.zl.erp.utils.EasyPoiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 仓库管理
 * @Author: zhutao
 * @Date: 2019/9/24
 */
@Slf4j
@Service
public class WarehouseInventoryService {

    private final WarehouseInventoryManageRepository warehouseRepository;

    private final WarehouseInventoryRepository inventoryRepository;

    @Autowired
    public WarehouseInventoryService(WarehouseInventoryManageRepository warehouseRepository, WarehouseInventoryRepository inventoryRepository) {
        this.warehouseRepository = warehouseRepository;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * 库存分页列表
     *
     * @param requestPage 分页入参
     * @return 分页列表
     */
    public ResponseDataPage queryWarehouseInventoryForPage(RequestDataPage<WarehouseInventoryEntity> requestPage) {
        WarehouseInventoryEntity manageParams = requestPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(manageParams, filterTerms);
        log.info("[库存分页列表]构造后参数：{}, 过滤条件：{}", manageParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestPage.getPageIndex(), requestPage.getPageSize());
        try {
            Page<WarehouseInventoryEntity> pageResult = inventoryRepository.commonQueryByMultiParamWithPage(manageParams, filterTerms, pageable, new FilterOrder("createTime", FilterKeyword.DESC));
            List<WarehouseInventoryEntity> resultList = pageResult.getContent();
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            responsePageEntity.setData(resultList);
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            return responsePageEntity;

        } catch (Exception ex) {
            log.error("[库存分页列表]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 库存导出
     *
     * @param request 入参
     */
    public void exportWarehouseInventory(RequestData<WarehouseInventoryEntity> request, HttpServletResponse response) {
        List<FilterTerm> filterTerms = new ArrayList<>();
        WarehouseInventoryEntity manageParams = request.getBody();
        splicingParameter(manageParams, filterTerms);
        log.info("[库存导出]构造后参数：{}, 过滤条件：{}", manageParams.toString(), filterTerms.toString());
        try {
            List<WarehouseInventoryEntity> resultList = inventoryRepository.commonQueryByMultiParam(manageParams, filterTerms, new FilterOrder("createTime", FilterKeyword.DESC));
            EasyPoiUtils.exportExcel(resultList, "库存管理", "库存管理报表", WarehouseInventoryEntity.class, "库存管理.xls", response);
        } catch (Exception ex) {
            log.error("[库存导出]查询异常：{}", ex);
        }
    }

    /**
     * 获取库存详情
     *
     * @param requestData 查询参数
     * @return 库存详情
     */
    public ResponseData getWarehouseInventoryDetail(RequestData<WarehouseInventoryEntity> requestData) {
        WarehouseInventoryEntity params = requestData.getBody();
        try {
            return CommonDataUtils.responseSuccess(inventoryRepository.getByStockId(params.getStockId()));
        } catch (Exception ex) {
            log.error("[库存详情]查询异常：{}", ex);
            return CommonDataUtils.responseFailure();
        }
    }

    /**
     * 参数构造
     *
     * @param manageParams 查询参数
     * @param filterTerms  过滤条件
     */
    private void splicingParameter(WarehouseInventoryEntity manageParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNotNullOrEmpty(manageParams.getConsumerName())) {
            filterTerms.add(new FilterTerm("consumerName", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(manageParams.getProductKindName())) {
            filterTerms.add(new FilterTerm("productKindName", FilterKeyword.LK));
        }
    }

}
