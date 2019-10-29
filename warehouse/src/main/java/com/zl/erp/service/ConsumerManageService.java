package com.zl.erp.service;

import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.entity.ConsumerManageRecordEntity;
import com.zl.erp.entity.DictionaryBean;
import com.zl.erp.entity.MaterialKindManageEntity;
import com.zl.erp.entity.WarehousePurchaseSellingRecordEntity;
import com.zl.erp.repository.ConsumerManageRepository;
import com.zl.erp.repository.MaterialKindManageRepository;
import com.zl.erp.repository.WarehouseOrderRepository;
import com.zl.erp.repository.WarehousePurchaseSellingRepository;
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

import static com.zl.erp.constants.CommonConstants.ERROR_PARAMS;
import static com.zl.erp.constants.CommonConstants.EXISTS_CONSUMER_USER_NAME_ERROR;

/**
 * @Description: 客户管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Slf4j
@Service
public class ConsumerManageService {

    private final ConsumerManageRepository manageRepository;

    private final MaterialKindManageRepository kindManageRepository;

    private final WarehousePurchaseSellingRepository sellingRepository;

    private final WarehouseOrderRepository orderRepository;

    @Autowired
    public ConsumerManageService(ConsumerManageRepository manageRepository, MaterialKindManageRepository kindManageRepository, WarehousePurchaseSellingRepository sellingRepository, WarehouseOrderRepository orderRepository) {
        this.manageRepository = manageRepository;
        this.kindManageRepository = kindManageRepository;
        this.sellingRepository = sellingRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * 查询客户管理分页列表
     *
     * @param requestPage 分页入参
     * @return 分页列表
     */
    public ResponseDataPage queryConsumerManageForPage(RequestDataPage<ConsumerManageRecordEntity> requestPage) {
        ConsumerManageRecordEntity manageRecordParams = requestPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(manageRecordParams, filterTerms);
        log.info("[客户信息分页查询]构造后参数：{}, 过滤条件：{}", manageRecordParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestPage.getPageIndex(), requestPage.getPageSize());
        try {
            Page<ConsumerManageRecordEntity> pageResult = manageRepository.commonQueryByMultiParamWithPage(manageRecordParams, filterTerms, pageable, new FilterOrder("createTime", FilterKeyword.DESC));
            List<ConsumerManageRecordEntity> resultList = pageResult.getContent();
            resultList.forEach(consumer -> consumer.setConsumerType("0".equals(consumer.getConsumerType()) ? "厂方" : "客户"));
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            responsePageEntity.setData(resultList);
            responsePageEntity.setPageIndex(requestPage.getPageIndex());
            responsePageEntity.setPageSize(requestPage.getPageSize());
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            return responsePageEntity;

        } catch (Exception ex) {
            log.error("[客户信息分页查询]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 导出用户信息
     *
     * @param requestData 请求入参
     * @param response    响应
     */
    public void exportConsumerManageRecord(RequestData<ConsumerManageRecordEntity> requestData, HttpServletResponse response) {
        ConsumerManageRecordEntity manageRecordParams = requestData.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(manageRecordParams, filterTerms);
        try {
            List<ConsumerManageRecordEntity> consumerManageRecordList = manageRepository.commonQueryByMultiParam(manageRecordParams, filterTerms, new FilterOrder("createTime", FilterKeyword.DESC));
            consumerManageRecordList.forEach(consumer -> consumer.setConsumerType("0".equals(consumer.getConsumerType()) ? "厂方" : "客户"));
            EasyPoiUtils.exportExcel(consumerManageRecordList, "客户列表", "客户信息报表", ConsumerManageRecordEntity.class, "客户报表.xls", response);
        } catch (Exception ex) {
            log.error("[导出用户信息]导出异常：{}", ex);
        }
    }

    /**
     * 新增用户信息
     *
     * @param requestData 请求体
     * @return 响应信息
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData saveConsumerManageRecord(RequestData<ConsumerManageRecordEntity> requestData) {
        ConsumerManageRecordEntity params = requestData.getBody();
        if (CodeHelper.isNullOrEmpty(params.getConsumerName()) || CodeHelper.isNullOrEmpty(params.getConsumerType()) || CodeHelper.isNullOrEmpty(params.getContactAddr())) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {
            if (CodeHelper.isNull(params.getConsumerId())) {
                Integer existsUser = manageRepository.checkExists(params.getConsumerName());
                if (existsUser > 0) {
                    return CommonDataUtils.responseFailure(EXISTS_CONSUMER_USER_NAME_ERROR);
                }
                params.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
            } else {
                Integer existsUser = manageRepository.checkExists(params.getConsumerName(), params.getConsumerId());
                if (existsUser > 0) {
                    return CommonDataUtils.responseFailure(EXISTS_CONSUMER_USER_NAME_ERROR);
                }
            }
            ConsumerManageRecordEntity consumer = manageRepository.save(params);
            return CommonDataUtils.responseSuccess(consumer);
        } catch (Exception ex) {
            log.error("[客户信息]新增异常：{}", ex);
            throw ex;
        }
    }

    /**
     * 删除用户信息
     *
     * @param consumerRecord 用户
     * @return 执行结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData deleteConsumerById(RequestData<ConsumerManageRecordEntity> consumerRecord) {
        ConsumerManageRecordEntity consumerParams = consumerRecord.getBody();
        try {
            assert consumerParams != null;
            if (CodeHelper.isNull(consumerParams.getConsumerId()) && CodeHelper.isNull(consumerParams.getConsumerType())) {
                return CommonDataUtils.responseFailure(ERROR_PARAMS);
            }
            List<MaterialKindManageEntity> kindManageList = kindManageRepository.getAllByConsumerId(consumerParams.getConsumerId());
            if (CodeHelper.isNotNullOrEmpty(kindManageList)) {
                return CommonDataUtils.responseFailure("该厂商存在物料信息，请勿删除！");
            }
            List<WarehousePurchaseSellingRecordEntity> sellingList = sellingRepository.queryAllByConsumerId(consumerParams.getConsumerId());
            if (CodeHelper.isNotNullOrEmpty(sellingList)) {
                return CommonDataUtils.responseFailure("该客户存在订单信息，请勿删除！");
            }
            Integer count = orderRepository.checkExistsByConsumerId(consumerParams.getConsumerId());
            if (count > 0) {
                return CommonDataUtils.responseFailure("该客户存在订单信息，请勿删除！");
            }
            manageRepository.deleteById(consumerParams.getConsumerId());
            return CommonDataUtils.responseSuccess();
        } catch (Exception ex) {
            log.error("[删除客户信息]：{}", ex);
            throw ex;
        }
    }

    /**
     * 查看用户详情信息
     *
     * @param requestData 请求
     * @return 用户详情
     */
    public ResponseData getConsumerManageDetail(RequestData<ConsumerManageRecordEntity> requestData) {
        try {
            ConsumerManageRecordEntity requestParams = requestData.getBody();
            if (CodeHelper.isNull(requestParams.getConsumerId())) {
                return CommonDataUtils.responseFailure("客户ID不可为空！");
            }
            ConsumerManageRecordEntity detail = manageRepository.getByConsumerId(requestParams.getConsumerId());
            requestParams.setConsumerType("0".equals(detail.getConsumerType()) ? "厂方" : "客户");
            return CommonDataUtils.responseSuccess(manageRepository.getByConsumerId(requestParams.getConsumerId()));
        } catch (Exception ex) {
            log.error("[客户信息详情]查看详情异常：{}", ex);
            return CommonDataUtils.responseFailure("查看用户详情失败，请重试！");
        }
    }

    /**
     * 厂方信息
     *
     * @return 厂方信息
     */
    public ResponseData getConsumerListByType() {
        try {
            List<ConsumerManageRecordEntity> consumerManageRecordList = manageRepository.getConsumerListByType();
            List<DictionaryBean> dictionaryBeans = new ArrayList<>();
            consumerManageRecordList.forEach(consumer -> {
                if(consumer.getConsumerId() != 0){
                    DictionaryBean dic = new DictionaryBean();
                    dic.setKey(consumer.getConsumerId());
                    dic.setValue(consumer.getConsumerName());
                    dictionaryBeans.add(dic);
                }
            });
            return CommonDataUtils.responseSuccess(dictionaryBeans);
        } catch (Exception ex) {
            log.error("[获取厂方缓存信息出错]", ex);
        }
        return CommonDataUtils.errorPageResponse();
    }

    /**
     * 查询参数构造
     *
     * @param manageRecordParams 查询参数
     * @param filterTerms        过滤条件
     */
    private void splicingParameter(ConsumerManageRecordEntity manageRecordParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNull(manageRecordParams)) {
            return;
        }
        if (CodeHelper.isNotNullOrEmpty(manageRecordParams.getConsumerName())) {
            filterTerms.add(new FilterTerm("consumerName", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNull(manageRecordParams.getConsumerType())) {
            filterTerms.add(new FilterTerm("consumerType", FilterKeyword.EQ));
        }
        if (CodeHelper.isNotNullOrEmpty(manageRecordParams.getContactPhone())) {
            filterTerms.add(new FilterTerm("contactPhone", FilterKeyword.LK));
        }
        if (CodeHelper.isNotNullOrEmpty(manageRecordParams.getContactAddr())) {
            filterTerms.add(new FilterTerm("contactAddr", FilterKeyword.LK));
        }
    }
}
