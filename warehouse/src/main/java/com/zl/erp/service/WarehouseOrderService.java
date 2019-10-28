package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.constants.CommonConstants;
import com.zl.erp.entity.*;
import com.zl.erp.repository.*;
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
 * @Description: 订单
 * @Author: zhutao
 * @Date: 2019/9/26
 */
@Service
@Slf4j
public class WarehouseOrderService {

    private final WarehouseOrderRepository orderRepository;

    private final PurchaseSellingOrderRepository sellingOrderRepository;

    private final WarehousePurchaseSellingRepository sellingRepository;

    private final FinanceFlowRecordRepository financeFlowRepository;

    private final WarehouseInventoryManageRepository inventoryRepository;

    private final MaterialKindManageRepository manageRepository;

    @Autowired
    public WarehouseOrderService(WarehouseOrderRepository orderRepository, PurchaseSellingOrderRepository sellingOrderRepository, WarehousePurchaseSellingRepository sellingRepository, FinanceFlowRecordRepository financeFlowRepository, WarehouseInventoryManageRepository inventoryRepository, MaterialKindManageRepository manageRepository) {
        this.orderRepository = orderRepository;
        this.sellingOrderRepository = sellingOrderRepository;
        this.sellingRepository = sellingRepository;
        this.financeFlowRepository = financeFlowRepository;
        this.inventoryRepository = inventoryRepository;
        this.manageRepository = manageRepository;
    }

    /**
     * 查询订单分页信息
     *
     * @param requestDataPage 分页查询参数
     * @return 订单分页信息
     */
    public ResponseDataPage queryWarehouseOrderForPage(RequestDataPage<WarehouseOrderEntity> requestDataPage) {
        WarehouseOrderEntity orderParams = requestDataPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(orderParams, filterTerms);
        log.info("[订单分页信息]构造后参数：{}, 过滤条件：{}", orderParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestDataPage.getPageIndex(), requestDataPage.getPageSize());
        try {
            Page<WarehouseOrderEntity> pageResult = orderRepository.commonQueryByMultiParamWithPage(orderParams, filterTerms, pageable, new FilterOrder("createTime", FilterKeyword.DESC));
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            List<WarehouseOrderEntity> resultList = pageResult.getContent();
            integrationData(resultList);
            responsePageEntity.setPageIndex(requestDataPage.getPageIndex());
            responsePageEntity.setPageSize(requestDataPage.getPageSize());
            responsePageEntity.setData(resultList);
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            return responsePageEntity;
        } catch (Exception ex) {
            log.error("[订单分页信息]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 导出订单分页信息
     *
     * @param requestData 分页查询参数
     * @param response    response
     */
    public void exportWarehouseOrder(RequestData<WarehouseOrderEntity> requestData, HttpServletResponse response) {
        WarehouseOrderEntity orderParams = requestData.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(orderParams, filterTerms);
        log.info("[订单分页信息]构造后参数：{}, 过滤条件：{}", orderParams.toString(), filterTerms.toString());
        try {
            List<WarehouseOrderEntity> resultList = orderRepository.commonQueryByMultiParam(orderParams, filterTerms, new FilterOrder("createTime", FilterKeyword.DESC));
            integrationData(resultList);
            EasyPoiUtils.exportExcel(resultList, "订单管理", "订单管理报表", WarehouseOrderEntity.class, "订单管理.xls", response);
        } catch (Exception ex) {
            log.error("[订单分页信息]查询异常：{}", ex);
        }
    }

    /**
     * 查询订单详情信息
     *
     * @param requestData 查询参数
     * @return 订单
     */
    public ResponseData getWarehouseOrderDetail(RequestData<WarehouseOrderEntity> requestData) {
        WarehouseOrderEntity orderParams = requestData.getBody();
        if (CodeHelper.isNull(orderParams.getOrderId())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        try {
            return CommonDataUtils.responseSuccess(orderRepository.getByOrderId(orderParams.getOrderId()));
        } catch (Exception ex) {
            log.error("[订单详情]查询异常：{}", ex);
            return CommonDataUtils.responseFailure();
        }
    }

    /**
     * 删除订单信息
     *
     * @param requestData 请求入参
     * @return 响应信息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData deleteWarehouseOrderById(RequestData<WarehouseOrderEntity> requestData) {
        WarehouseOrderEntity orderParams = requestData.getBody();
        if (CodeHelper.isNull(orderParams.getOrderId()) || CodeHelper.isNull(orderParams.getTradeType())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        try {
            if (!ORDER_PLACED.equals(orderParams.getTradeType())) {
                return CommonDataUtils.responseFailure("当前订单状态不支持删除！");
            }
            sellingOrderRepository.deleteById(orderParams.getOrderId());
            return CommonDataUtils.responseSuccess();
        } catch (Exception ex) {
            log.error("[订单详情]查询异常：{}", ex);
            throw ex;
        }
    }

    /**
     * 发货
     *
     * @param requestData 请求入参
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData doDelivered(RequestData<WarehouseOrderEntity> requestData) throws Exception {
        WarehouseOrderEntity orderParams = requestData.getBody();
        if (CodeHelper.isNull(orderParams.getOrderId())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        WarehouseOrderEntity orderInfo = orderRepository.getByOrderId(orderParams.getOrderId());
        // 新增进售货记录
        WarehousePurchaseSellingRecordEntity record = new WarehousePurchaseSellingRecordEntity();
        String tradeType = orderInfo.getTradeType();
        if (!CommonConstants.ORDER_PLACED.equals(tradeType)) {
            return CommonDataUtils.responseFailure();
        }
        record.setConsumerId(orderInfo.getConsumerId());
        record.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
        record.setOrderId(orderInfo.getOrderId());
        record.setProductKindId(orderInfo.getProductKindId());
        record.setStockNum(orderInfo.getStockNum());
        record.setManageType(CommonConstants.ORDER_DELIVER_GOODS);
        record.setPurchasePrice(orderInfo.getPurchasePrice());
        MaterialKindManageEntity materialKindCache = manageRepository.getMaterialKindById(orderInfo.getProductKindId());
        record.setSellingPrice(materialKindCache.getSellingPrice());
        sellingRepository.save(record);
        sellingOrderRepository.updateOrderInfoByOrderId(CommonConstants.ORDER_DELIVER_GOODS, orderInfo.getOrderId());
        // 更新或新增库存信息
        boolean inventoryFlag = addWarehouseInventoryManage(orderInfo);
        if (!inventoryFlag) {
            throw new Exception("新增库存信息异常");
        }
        return CommonDataUtils.responseSuccess();
    }

    /**
     * 新增库存信息
     *
     * @param orderInfo 订单
     * @return boolean true: 新增完毕
     */
    private boolean addWarehouseInventoryManage(WarehouseOrderEntity orderInfo) {
        WarehouseInventoryManageEntity inventoryManage = inventoryRepository.getByProductKindId(orderInfo.getProductKindId());
        boolean newInventoryFlag = false;
        if (CodeHelper.isNull(inventoryManage)) {
            inventoryManage = new WarehouseInventoryManageEntity();
            newInventoryFlag = true;
        }
        MaterialKindManageEntity kindManage = manageRepository.getMaterialKindById(orderInfo.getProductKindId());
        BigDecimal sellingPrice = new BigDecimal(kindManage.getSellingPrice());
        switch (Integer.parseInt(orderInfo.getManageType())) {
            case MANAGE_TYPE_RETURNED_TO_FACTORY:
                inventoryManage(orderInfo, inventoryManage, sellingPrice, newInventoryFlag, MANAGE_TYPE_RETURNED_TO_FACTORY);
                return true;
            case MANAGE_TYPE_PURCHASE:
                inventoryManage(orderInfo, inventoryManage, sellingPrice, newInventoryFlag, MANAGE_TYPE_PURCHASE);
                return true;
            case MANAGE_TYPE_SALES:
                inventoryManage(orderInfo, inventoryManage, sellingPrice, newInventoryFlag, MANAGE_TYPE_SALES);
                return true;
            case MANAGE_TYPE_RETURNED_PURCHASE:
                inventoryManage(orderInfo, inventoryManage, sellingPrice, newInventoryFlag, MANAGE_TYPE_RETURNED_PURCHASE);
                return true;
            default:
                break;
        }
        return false;
    }

    /**
     * @param orderInfo       订单信息
     * @param inventoryManage 库存信息
     * @param sellingPrice    售价
     * @param newFlag         标志
     * @param manageType      类型
     */
    private void inventoryManage(WarehouseOrderEntity orderInfo, WarehouseInventoryManageEntity inventoryManage, BigDecimal sellingPrice, boolean newFlag, int manageType) {
        String stockNum = orderInfo.getStockNum();
        String totalAmount;
        if (newFlag) {
            inventoryManage.setProductKindId(orderInfo.getProductKindId());
            inventoryManage.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
            totalAmount = CommonDataUtils.formatToString(sellingPrice.multiply(new BigDecimal(orderInfo.getStockNum())));
        } else {
            if (MANAGE_TYPE_RETURNED_TO_FACTORY == manageType || MANAGE_TYPE_SALES == manageType) {
                BigDecimal stockNums = new BigDecimal(inventoryManage.getStockNum()).subtract(new BigDecimal(orderInfo.getStockNum()));
                totalAmount = CommonDataUtils.formatToString(sellingPrice.multiply(stockNums));
            } else if (MANAGE_TYPE_RETURNED_PURCHASE == manageType) {
                BigDecimal stockNums = new BigDecimal(inventoryManage.getStockNum()).subtract(new BigDecimal(orderInfo.getStockNum()));
                totalAmount = CommonDataUtils.formatToString(sellingPrice.multiply(stockNums));
            } else {
                return;
            }
        }
        inventoryManage.setStockNum(stockNum);
        inventoryManage.setTotalAmount(totalAmount);
        inventoryRepository.save(inventoryManage);
    }

    /**
     * 支付
     *
     * @param requestData 请求入参
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData doPayment(RequestData<WarehouseOrderEntity> requestData) {
        WarehouseOrderEntity orderParams = requestData.getBody();
        if (CodeHelper.isNull(orderParams.getOrderId())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        try {
            PurchaseSellingOrderRecordEntity orderRecord = sellingOrderRepository.getByOrderId(orderParams.getOrderId());
            if (!CommonConstants.ORDER_DELIVER_GOODS.equals(orderRecord.getTradeType())) {
                return CommonDataUtils.responseFailure("订单状态不是发货状态，请勿操作操作！");
            }
            orderRecord.setTradeType(CommonConstants.ORDER_PAY);
            sellingOrderRepository.save(orderRecord);
            // 新增流水信息
            FinanceFlowRecordEntity flowRecord = new FinanceFlowRecordEntity();
            flowRecord.setFlowNumber(CommonDataUtils.getUUID());
            flowRecord.setOrderId(orderRecord.getOrderId());
            flowRecord.setProductKindId(orderRecord.getProductKindId());
            List<String> deductTypeList = Arrays.asList(String.valueOf(MANAGE_TYPE_RETURNED_TO_FACTORY), String.valueOf(CommonConstants.MANAGE_TYPE_RETURNED_PURCHASE));
            if (deductTypeList.contains(String.valueOf(orderRecord.getManageType()))) {
                flowRecord.setFlowAmount(CommonDataUtils.formatToString(CommonDataUtils.decimalToMinus(new BigDecimal(orderRecord.getTotalAmount()))));
            } else {
                flowRecord.setFlowAmount(orderRecord.getTotalAmount());
            }
            flowRecord.setFlowType(orderRecord.getManageType());
            flowRecord.setFlowTime(CommonDataUtils.getFormatDateString(new Date()));
            flowRecord.setConsumerId(orderRecord.getConsumerId());
            financeFlowRepository.save(flowRecord);
            return CommonDataUtils.responseSuccess();
        } catch (Exception ex) {
            log.error("[发货]异常信息：{}", ex);
            throw ex;
        }
    }

    /**
     * 数据处理
     *
     * @param resultList 结果集
     */
    private void integrationData(List<WarehouseOrderEntity> resultList) {
        resultList.forEach(order -> {
            order.setTradeType(getTradeTypeMap().get(order.getTradeType()));
            order.setManageType(getTradeStatusMap().get(order.getManageType()));
        });
    }

    /**
     * 参数构造
     *
     * @param orderParams 查询参数
     * @param filterTerms 过滤条件
     */
    private void splicingParameter(WarehouseOrderEntity orderParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNotNullOrEmpty(orderParams.getTradeType())) {
            filterTerms.add(new FilterTerm("tradeType", FilterKeyword.EQ));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getProductKindName())) {
            filterTerms.add(new FilterTerm("productKindName", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getConsumerName())) {
            filterTerms.add(new FilterTerm("consumerName", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getCreateStartTime()) && CodeHelper.isNotNullOrEmpty(orderParams.getCreateEndTime())) {
            orderParams.setCreateTime(orderParams.getCreateStartTime() + "#" + orderParams.getCreateEndTime());
            filterTerms.add(new FilterTerm("createTime", FilterKeyword.BETWEEN));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getCreateStartTime()) && CodeHelper.isNullOrEmpty(orderParams.getCreateEndTime())) {
            orderParams.setCreateTime(orderParams.getCreateStartTime() + "#" + CommonDataUtils.getFormatDateString(new Date()));
            filterTerms.add(new FilterTerm("createTime", FilterKeyword.BETWEEN));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getCreateStartTime()) && CodeHelper.isNotNullOrEmpty(orderParams.getCreateEndTime())) {
            orderParams.setCreateTime("1990-08-08 00:00:00" + "#" + orderParams.getCreateEndTime());
            filterTerms.add(new FilterTerm("createTime", FilterKeyword.BETWEEN));
        }
    }

    /**
     * 交易状态
     *
     * @return 交易状态
     */
    private Map<String, String> getTradeStatusMap() {
        Map<String, String> tradeStatusMap = new HashMap<>(3);
        tradeStatusMap.put("0", "退还厂方");
        tradeStatusMap.put("1", "进货");
        tradeStatusMap.put("2", "售货");
        tradeStatusMap.put("3", "退货");

        return tradeStatusMap;
    }

    /**
     * 交易类型
     *
     * @return 交易类型
     */
    private Map<String, String> getTradeTypeMap() {
        Map<String, String> tradeStatusMap = new HashMap<>(4);
        tradeStatusMap.put("0", "已下单");
        tradeStatusMap.put("1", "已发货");
        tradeStatusMap.put("2", "已付款");
        return tradeStatusMap;
    }
}
