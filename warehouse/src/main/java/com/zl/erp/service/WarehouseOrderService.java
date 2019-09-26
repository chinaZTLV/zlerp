package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.constants.CommonConstants;
import com.zl.erp.entity.WarehouseOrderEntity;
import com.zl.erp.repository.PurchaseSellingOrderRepository;
import com.zl.erp.repository.WarehouseOrderRepository;
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
import java.util.*;

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

    @Autowired
    public WarehouseOrderService(WarehouseOrderRepository orderRepository, PurchaseSellingOrderRepository sellingOrderRepository) {
        this.orderRepository = orderRepository;
        this.sellingOrderRepository = sellingOrderRepository;
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
        if (CodeHelper.isNull(orderParams.getOrderId())) {
            return CommonDataUtils.responseFailure(CommonConstants.ERROR_PARAMS);
        }
        try {
            sellingOrderRepository.deleteById(orderParams.getOrderId());
            return CommonDataUtils.responseSuccess();
        } catch (Exception ex) {
            log.error("[订单详情]查询异常：{}", ex);
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
        tradeStatusMap.put("0", "已下单");
        tradeStatusMap.put("1", "已发货");
        tradeStatusMap.put("2", "已付款");
        return tradeStatusMap;
    }

    /**
     * 交易类型
     *
     * @return 交易类型
     */
    private Map<String, String> getTradeTypeMap() {
        Map<String, String> tradeStatusMap = new HashMap<>(4);
        tradeStatusMap.put("0", "退还厂方");
        tradeStatusMap.put("1", "进货");
        tradeStatusMap.put("2", "售货");
        tradeStatusMap.put("3", "退货");
        return tradeStatusMap;
    }
}
