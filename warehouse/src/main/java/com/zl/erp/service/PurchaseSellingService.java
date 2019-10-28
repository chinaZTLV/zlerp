package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.entity.PurchaseSellingRecordEntity;
import com.zl.erp.entity.WarehouseInventoryEntity;
import com.zl.erp.repository.PurchaseSellingRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 进售货记录
 * @Author: zhutao
 * @Date: 2019/9/30
 */
@Slf4j
@Service
public class PurchaseSellingService {

    private final PurchaseSellingRepository sellingRepository;

    @Autowired
    public PurchaseSellingService(PurchaseSellingRepository sellingRepository) {
        this.sellingRepository = sellingRepository;
    }


    /**
     * 操作记录分页列表
     *
     * @param requestPage 分页入参
     * @return 分页列表
     */
    public ResponseDataPage queryPurchaseSellingForPage(RequestDataPage<PurchaseSellingRecordEntity> requestPage) {
        PurchaseSellingRecordEntity manageParams = requestPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(manageParams, filterTerms);
        log.info("[操作记录分页列表]构造后参数：{}, 过滤条件：{}", manageParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestPage.getPageIndex(), requestPage.getPageSize());
        try {
            Page<PurchaseSellingRecordEntity> pageResult = sellingRepository.commonQueryByMultiParamWithPage(manageParams, filterTerms, pageable, new FilterOrder("createTime", FilterKeyword.DESC));
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            List<PurchaseSellingRecordEntity> resultList = pageResult.getContent();
            resultList.forEach(record -> record.setManageType(getTradeTypeMap().get(record.getManageType())));
            responsePageEntity.setData(resultList);
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            responsePageEntity.setPageIndex(requestPage.getPageIndex());
            responsePageEntity.setPageSize(requestPage.getPageSize());
            return responsePageEntity;
        } catch (Exception ex) {
            log.error("[操作记录分页列表]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 操作记录导出
     *
     * @param request 入参
     */
    public void exportPurchaseSellingRecord(RequestData<PurchaseSellingRecordEntity> request, HttpServletResponse response) {
        List<FilterTerm> filterTerms = new ArrayList<>();
        PurchaseSellingRecordEntity manageParams = request.getBody();
        splicingParameter(manageParams, filterTerms);
        log.info("[操作记录导出]构造后参数：{}, 过滤条件：{}", manageParams.toString(), filterTerms.toString());
        try {
            List<PurchaseSellingRecordEntity> resultList = sellingRepository.commonQueryByMultiParam(manageParams, filterTerms, new FilterOrder("createTime", FilterKeyword.DESC));
            resultList.forEach(record -> record.setManageType(getTradeTypeMap().get(record.getManageType())));
            EasyPoiUtils.exportExcel(resultList, "操作记录", "操作记录报表", WarehouseInventoryEntity.class, "操作记录.xls", response);
        } catch (Exception ex) {
            log.error("[操作记录导出]查询异常：{}", ex);
        }
    }

    /**
     * 查询参数构造
     *
     * @param manageParams 查询参数
     * @param filterTerms  过滤条件
     */
    private void splicingParameter(PurchaseSellingRecordEntity manageParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNotNullOrEmpty(manageParams.getProductKindName())) {
            filterTerms.add(new FilterTerm("productKindName", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(manageParams.getManageType())) {
            filterTerms.add(new FilterTerm("manageType", FilterKeyword.EQ));
        }
        if (CodeHelper.isNotNullOrEmpty(manageParams.getConsumerName())) {
            filterTerms.add(new FilterTerm("consumerName", FilterKeyword.LK));
        }
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
        tradeStatusMap.put("2", "退货");
        tradeStatusMap.put("3", "售货");
        return tradeStatusMap;
    }
}
