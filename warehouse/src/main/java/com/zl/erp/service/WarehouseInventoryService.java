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
            resultList.forEach(inventory -> inventory.setUnit(getUnitName(inventory.getUnit())));
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
                purchaseSellParams.setDiscount("100");
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
        BigDecimal discount = new BigDecimal(purchaseSellParams.getDiscount()).divide(new BigDecimal("100"), 2, 4);
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
    public ResponseData placingAnOrderVersion2(RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        PurchaseSellingOrderRecordEntity params = requestData.getBody();
        if (checkPlacingAnOrderParams(params)) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {

            if (MANAGE_TYPE_RETURNED_PURCHASE == params.getManageType()) {
                Integer sellCount = sellingOrderRepository.getTotalStockCount(params.getConsumerId(), MANAGE_TYPE_SALES);
                Integer returnCount = sellingOrderRepository.getTotalStockCount(params.getConsumerId(), MANAGE_TYPE_RETURNED_PURCHASE);
                if (Integer.parseInt(params.getStockNum()) > (sellCount - returnCount)) {
                    return CommonDataUtils.responseFailure("当前用户购买的数量不足以退货！");
                }
            }
            List<Integer> ownManageTypeList = Arrays.asList(MANAGE_TYPE_PURCHASE, MANAGE_TYPE_RETURNED_TO_FACTORY);
            List<Integer> manageTypeList = Arrays.asList(MANAGE_TYPE_SALES, MANAGE_TYPE_RETURNED_TO_FACTORY);
            if (manageTypeList.contains(params.getManageType())) {
                WarehouseInventoryEntity warehouseInventory = inventoryRepository.getByProductKindId(params.getProductKindId());
                int stockNum = CodeHelper.isNotNull(warehouseInventory) ? warehouseInventory.getStockNum() : 0;
                if (Integer.parseInt(params.getStockNum()) > stockNum) {
                    return CommonDataUtils.responseFailure("库存不足！");
                }
            }
            BigDecimal unitPrice;
            MaterialKindManageEntity kindManage = materialRepository.getMaterialKindById(params.getProductKindId());
            if (ownManageTypeList.contains(params.getManageType())) {
                // 进货、退还厂方 无折扣、折扣金额、利润等
                params.setDiscount("100");
                params.setDiscountAmount("0");
                params.setNetReceipt("0");
                // 插入一个默认的用户
                params.setConsumerId(1);
                unitPrice = new BigDecimal(kindManage.getPurchasePrice());
            } else {
                if (CodeHelper.isNull(params.getConsumerId())) {
                    return CommonDataUtils.responseFailure("请选择用户！");
                }
                unitPrice = new BigDecimal(kindManage.getSellingPrice());
                BigDecimal discount = new BigDecimal(params.getDiscount()).divide(new BigDecimal("100"), 2, 4);
                BigDecimal sellingPrice = unitPrice.multiply(new BigDecimal(params.getStockNum()));
                BigDecimal purchasePrice = new BigDecimal(kindManage.getPurchasePrice()).multiply(new BigDecimal(params.getStockNum()));
                BigDecimal discountAmount = sellingPrice.subtract(discount.multiply(sellingPrice));
                if (discountAmount.compareTo(new BigDecimal(DEFAULT_VALUE)) < 0) {
                    // 转为正数
                    BigDecimal discountAmo = new BigDecimal(CommonDataUtils.formatToString(discountAmount).replace("-", ""));
                    params.setDiscountAmount(CommonDataUtils.formatToString(discountAmo));
                    params.setNetReceipt(CommonDataUtils.formatToString(sellingPrice.add(purchasePrice).add(discountAmo)));

                } else {

                    params.setDiscountAmount(CommonDataUtils.formatToString(CommonDataUtils.decimalToMinus(discountAmount)));
                    params.setNetReceipt(CommonDataUtils.formatToString(sellingPrice.subtract(purchasePrice).subtract(discountAmount)));
                }
            }
            BigDecimal orderAmount = getOrderAmount(params, unitPrice);
            params.setUnitPrice(CommonDataUtils.formatToString(unitPrice));
            params.setTotalAmount(CommonDataUtils.formatToString(orderAmount));
            params.setPurchasePrice(kindManage.getPurchasePrice());
            params.setTradeType(ORDER_PLACED);
            params.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
            return CommonDataUtils.responseSuccess(sellingOrderRepository.save(params));
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
                || CodeHelper.isNullOrEmpty(purchaseSellParams.getStockNum()) || CodeHelper.isNull(purchaseSellParams.getManageType());
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

    /**
     * 获取物料库存
     *
     * @param requestData 请求参数
     * @return 结果集
     */
    public ResponseData getMaterialCountByKindId(RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        PurchaseSellingOrderRecordEntity params = requestData.getBody();
        if (CodeHelper.isNull(params.getProductKindId())) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        WarehouseInventoryEntity warehouseInventory = inventoryRepository.getByProductKindId(params.getProductKindId());
        Map<String, Integer> stockNumMap = new HashMap<>();
        if (CodeHelper.isNull(warehouseInventory)) {
            stockNumMap.put("stockNum", 0);
            return CommonDataUtils.responseSuccess(stockNumMap);
        }
        stockNumMap.put("stockNum", warehouseInventory.getStockNum());
        return CommonDataUtils.responseSuccess(stockNumMap);
    }
}
