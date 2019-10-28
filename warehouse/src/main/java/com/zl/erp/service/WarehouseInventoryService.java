package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.constants.CommonConstants;
import com.zl.erp.entity.MaterialKindManageEntity;
import com.zl.erp.entity.PurchaseSellingOrderRecordEntity;
import com.zl.erp.entity.WarehouseInventoryEntity;
import com.zl.erp.repository.MaterialKindManageRepository;
import com.zl.erp.repository.PurchaseSellingOrderRepository;
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
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

import static com.zl.erp.constants.CommonConstants.*;

/**
 * @Description: 仓库管理
 * @Author: zhutao
 * @Date: 2019/9/24
 */
@Slf4j
@Service
public class WarehouseInventoryService {

    private final WarehouseInventoryRepository inventoryRepository;

    private final PurchaseSellingOrderRepository sellingOrderRepository;

    private final MaterialKindManageRepository materialRepository;

    @Autowired
    public WarehouseInventoryService(WarehouseInventoryRepository inventoryRepository, PurchaseSellingOrderRepository sellingOrderRepository, MaterialKindManageRepository materialRepository) {
        this.inventoryRepository = inventoryRepository;
        this.sellingOrderRepository = sellingOrderRepository;
        this.materialRepository = materialRepository;
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
            responsePageEntity.setPageIndex(requestPage.getPageIndex());
            responsePageEntity.setPageSize(requestPage.getPageSize());
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
            resultList.forEach(inventory -> inventory.setUnit(getUnitName(inventory.getUnit())));
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
     * 获取订单总金额
     *
     * @param requestData 请求入参
     * @return 响应结果
     */
    public ResponseData getOrderAmountInfo(RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        PurchaseSellingOrderRecordEntity purchaseSellParams = requestData.getBody();
        if (CodeHelper.isNullOrEmpty(purchaseSellParams.getDiscount()) || CodeHelper.isNull(purchaseSellParams.getProductKindId())
                || CodeHelper.isNullOrEmpty(purchaseSellParams.getStockNum()) || CodeHelper.isNull(purchaseSellParams.getManageType())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        try {
            MaterialKindManageEntity kindManage = materialRepository.getMaterialKindById(purchaseSellParams.getProductKindId());
            List<Integer> ownTradeTypeList = Arrays.asList(MANAGE_TYPE_PURCHASE, MANAGE_TYPE_RETURNED_TO_FACTORY);
            BigDecimal unitPrice;
            if (ownTradeTypeList.contains(purchaseSellParams.getManageType())) {
                unitPrice = new BigDecimal(kindManage.getPurchasePrice());
                purchaseSellParams.setConsumerId(kindManage.getConsumerId());
                // 进货、退还厂方 无折扣、折扣金额、利润等
                purchaseSellParams.setDiscount("1");
                purchaseSellParams.setDiscountAmount("0");
                purchaseSellParams.setNetReceipt("0");
            } else {
                unitPrice = new BigDecimal(kindManage.getSellingPrice());
                BigDecimal totalDiscountAmount = getOrderDiscountAmount(purchaseSellParams, unitPrice);
                purchaseSellParams.setDiscountAmount(CommonDataUtils.formatToString(totalDiscountAmount));
                BigDecimal purchaseSellAmount = getOrderAmount(purchaseSellParams, new BigDecimal(kindManage.getPurchasePrice()));
                purchaseSellParams.setNetReceipt(CommonDataUtils.formatToString(totalDiscountAmount.subtract(purchaseSellAmount)));
            }
            purchaseSellParams.setTotalAmount(CommonDataUtils.formatToString(getOrderAmount(purchaseSellParams, unitPrice)));
            purchaseSellParams.setUnitPrice(CommonDataUtils.formatToString(unitPrice));
            return CommonDataUtils.responseSuccess(purchaseSellParams);
        } catch (Exception ex) {
            log.error("[获取订单总金额]异常：{}", ex);
            return CommonDataUtils.responseFailure();
        }
    }

    /**
     * 获取订单总金额
     *
     * @param purchaseSellParams 参数
     * @return 金额
     */
    private BigDecimal getOrderAmount(PurchaseSellingOrderRecordEntity purchaseSellParams, BigDecimal unitPrice) {
        BigDecimal discount = new BigDecimal(purchaseSellParams.getDiscount());
        BigDecimal stockNum = new BigDecimal(purchaseSellParams.getStockNum());
        BigDecimal totalAmount = unitPrice.multiply(stockNum);
        return totalAmount.multiply(discount);
    }

    /**
     * 获取订单总金额
     *
     * @param purchaseSellParams 参数
     * @return 金额
     */
    private BigDecimal getOrderDiscountAmount(PurchaseSellingOrderRecordEntity purchaseSellParams, BigDecimal unitPrice) {
        BigDecimal stockNum = new BigDecimal(purchaseSellParams.getStockNum());
        BigDecimal totalAmount = unitPrice.multiply(stockNum);
        return totalAmount.subtract(getOrderAmount(purchaseSellParams, unitPrice));
    }

    /**
     * 下订单
     *
     * @param requestData 请求参数
     * @return 执行结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData placingAnOrder(RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        PurchaseSellingOrderRecordEntity purchaseSellParams = requestData.getBody();
        if (checkPlacingAnOrderParams(purchaseSellParams)) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {
            List<Integer> ownTradeTypeList = Arrays.asList(MANAGE_TYPE_PURCHASE, MANAGE_TYPE_RETURNED_TO_FACTORY);
            BigDecimal unitPrice;
            MaterialKindManageEntity kindManage = materialRepository.getMaterialKindById(purchaseSellParams.getProductKindId());
            if (ownTradeTypeList.contains(purchaseSellParams.getManageType())) {
                // 进货、退还厂方 无折扣、折扣金额、利润等
                purchaseSellParams.setDiscount("1");
                purchaseSellParams.setDiscountAmount("0");
                purchaseSellParams.setNetReceipt("0");
                unitPrice = new BigDecimal(kindManage.getPurchasePrice());
            } else {
                unitPrice = new BigDecimal(kindManage.getSellingPrice());
            }
            BigDecimal orderAmount = getOrderAmount(purchaseSellParams, unitPrice);
            BigDecimal totalAmount = new BigDecimal(purchaseSellParams.getTotalAmount());
            if (totalAmount.compareTo(orderAmount) != 0) {
                return CommonDataUtils.responseFailure("金额不一致！");
            }
            purchaseSellParams.setPurchasePrice(kindManage.getPurchasePrice());
            purchaseSellParams.setTradeType(ORDER_PLACED);
            purchaseSellParams.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
            return CommonDataUtils.responseSuccess(sellingOrderRepository.save(purchaseSellParams));
        } catch (Exception ex) {
            log.error("[下订单]异常信息:{}", ex);
            throw ex;
        }
    }

    /**
     * 检查参数
     *
     * @param purchaseSellParams 参数
     * @return true:通过 false:不通过
     */
    private boolean checkPlacingAnOrderParams(PurchaseSellingOrderRecordEntity purchaseSellParams) {
        return CodeHelper.isNullOrEmpty(purchaseSellParams.getDiscount()) || CodeHelper.isNull(purchaseSellParams.getProductKindId())
                || CodeHelper.isNullOrEmpty(purchaseSellParams.getStockNum()) || CodeHelper.isNull(purchaseSellParams.getConsumerId());
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

    /**
     * 单位
     *
     * @return 单位
     */
    private String getUnitName(String key) {
        Map<String, String> unitMap = new HashMap<>();
        unitMap.put("0", "块");
        unitMap.put("2", "米");
        unitMap.put("1", "个");
        unitMap.put("3", "平方");
        return unitMap.get(key);
    }

}
