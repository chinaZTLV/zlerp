package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.entity.FinanceFlowEntity;
import com.zl.erp.entity.WarehouseOrderEntity;
import com.zl.erp.repository.FinanceFlowRepository;
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
 * @Description: 财务流水
 * @Author: zhutao
 * @Date: 2019/9/27
 */
@Slf4j
@Service
public class FinanceFlowService {

    private final FinanceFlowRepository flowRepository;

    @Autowired
    public FinanceFlowService(FinanceFlowRepository flowRepository) {
        this.flowRepository = flowRepository;
    }


    /**
     * 查询流水分页信息
     *
     * @param requestDataPage 分页查询参数
     * @return 流水分页信息
     */
    public ResponseDataPage queryFinanceFlowForPage(RequestDataPage<FinanceFlowEntity> requestDataPage) {
        FinanceFlowEntity orderParams = requestDataPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(orderParams, filterTerms);
        log.info("[查询流水分页信息]构造后参数：{}, 过滤条件：{}", orderParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestDataPage.getPageIndex(), requestDataPage.getPageSize());
        try {
            Page<FinanceFlowEntity> pageResult = flowRepository.commonQueryByMultiParamWithPage(orderParams, filterTerms, pageable, new FilterOrder("flowTime", FilterKeyword.DESC));
            List<FinanceFlowEntity> resultList = pageResult.getContent();
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            integrationData(resultList);
            responsePageEntity.setPageIndex(requestDataPage.getPageIndex());
            responsePageEntity.setPageSize(requestDataPage.getPageSize());
            responsePageEntity.setData(resultList);
            return responsePageEntity;
        } catch (Exception ex) {
            log.error("[流水分页信息]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 导出财务管理
     *
     * @param requestData 请求参数
     * @param response    参数
     */
    public void exportFinanceFlowList(RequestData<FinanceFlowEntity> requestData, HttpServletResponse response) {
        FinanceFlowEntity orderParams = requestData.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(orderParams, filterTerms);
        log.info("[财务管理]构造后参数：{}, 过滤条件：{}", orderParams.toString(), filterTerms.toString());
        try {
            List<FinanceFlowEntity> resultList = flowRepository.commonQueryByMultiParam(orderParams, filterTerms, new FilterOrder("flowTime", FilterKeyword.DESC));
            integrationData(resultList);
            EasyPoiUtils.exportExcel(resultList, "财务管理", "财务管理报表", FinanceFlowEntity.class, "财务管理.xls", response);
        } catch (Exception ex) {
            log.error("[财务管理]查询异常：{}", ex);
        }
    }

    /**
     * 整合参数
     *
     * @param resultList 结果集
     */
    private void integrationData(List<FinanceFlowEntity> resultList) {
        resultList.forEach(finance -> finance.setFlowType(getFlowTypeMap(finance.getFlowType())));
    }

    /**
     * 获取值
     *
     * @param key key
     * @return value
     */
    private String getFlowTypeMap(String key) {
        Map<String, String> flowTypeMap = new HashMap<>(4);
        flowTypeMap.put("0", "退还厂方");
        flowTypeMap.put("2", "售货");
        flowTypeMap.put("1", "进货");
        flowTypeMap.put("3", "退货");
        return flowTypeMap.get(key);
    }

    /**
     * 查询参数拼接
     *
     * @param orderParams 请求参数
     * @param filterTerms 条件
     */
    private void splicingParameter(FinanceFlowEntity orderParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNotNull(orderParams.getOrderId())) {
            filterTerms.add(new FilterTerm("order_id", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(orderParams.getConsumerName())) {
            filterTerms.add(new FilterTerm("consumer_name", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNull(orderParams.getFlowType())) {
            filterTerms.add(new FilterTerm("flow_type", FilterKeyword.EQ));
        }
        if (CodeHelper.isNotNull(orderParams.getFlowId())) {
            filterTerms.add(new FilterTerm("flow_id", FilterKeyword.EQ));
        }
    }
}
