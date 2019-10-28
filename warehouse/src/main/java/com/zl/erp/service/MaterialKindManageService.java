package com.zl.erp.service;

import com.alibaba.fastjson.JSON;
import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.common.db.FilterKeyword;
import com.zl.erp.common.db.FilterOrder;
import com.zl.erp.common.db.FilterTerm;
import com.zl.erp.constants.CommonConstants;
import com.zl.erp.entity.ConsumerManageRecordEntity;
import com.zl.erp.entity.MaterialKindManageEntity;
import com.zl.erp.entity.WarehouseInventoryManageEntity;
import com.zl.erp.repository.ConsumerManageRepository;
import com.zl.erp.repository.MaterialKindManageRepository;
import com.zl.erp.repository.WarehouseInventoryManageRepository;
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

import static com.zl.erp.constants.CommonConstants.*;

/**
 * @Description: 物料管理
 * @Author: zhutao
 * @Date: 2019/9/18
 */
@Slf4j
@Service
public class MaterialKindManageService {

    private final ConsumerManageRepository consumerRepository;

    private final MaterialKindManageRepository materialRepository;

    private final RedisService redisService;

    private final BaseCacheService baseCacheService;

    private final WarehouseInventoryManageRepository manageRepository;

    @Autowired
    public MaterialKindManageService(ConsumerManageRepository consumerRepository, MaterialKindManageRepository materialRepository, RedisService redisService, BaseCacheService baseCacheService, WarehouseInventoryManageRepository manageRepository) {
        this.consumerRepository = consumerRepository;
        this.materialRepository = materialRepository;
        this.redisService = redisService;
        this.baseCacheService = baseCacheService;
        this.manageRepository = manageRepository;
    }

    /**
     * 查询物料类型管理分页列表
     *
     * @param requestPage 分页入参
     * @return 分页列表
     */
    public ResponseDataPage queryMaterialKindForPage(RequestDataPage<MaterialKindManageEntity> requestPage) {
        MaterialKindManageEntity kindManageParams = requestPage.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(kindManageParams, filterTerms);
        log.info("[物料类型管理]构造后参数：{}, 过滤条件：{}", kindManageParams.toString(), filterTerms.toString());
        Pageable pageable = PageRequest.of(requestPage.getPageIndex(), requestPage.getPageSize());
        try {
            Page<MaterialKindManageEntity> pageResult = materialRepository.commonQueryByMultiParamWithPage(kindManageParams, filterTerms, pageable, new FilterOrder("createTime", FilterKeyword.DESC));
            List<MaterialKindManageEntity> resultList = pageResult.getContent();
            ResponseDataPage responsePageEntity = CommonDataUtils.successPageResponse();
            integrationData(resultList);
            responsePageEntity.setData(resultList);
            responsePageEntity.setTotalPage(pageResult.getTotalPages());
            responsePageEntity.setPageIndex(requestPage.getPageIndex());
            responsePageEntity.setPageSize(requestPage.getPageSize());
            responsePageEntity.setTotalCount((int) pageResult.getTotalElements());
            return responsePageEntity;
        } catch (Exception ex) {
            log.error("[物料类型管理]查询异常：{}", ex);
            return CommonDataUtils.errorPageResponse();
        }
    }

    /**
     * 查询物料类型管理分页列表
     *
     * @param requestData 分页入参
     * @param response    响应
     */
    public void exportMaterialKindList(RequestData<MaterialKindManageEntity> requestData, HttpServletResponse response) {
        MaterialKindManageEntity kindManageParams = requestData.getBody();
        List<FilterTerm> filterTerms = new ArrayList<>();
        // 参数构造
        splicingParameter(kindManageParams, filterTerms);
        log.info("[物料类型管理]构造后参数：{}, 过滤条件：{}", kindManageParams.toString(), filterTerms.toString());
        try {
            List<MaterialKindManageEntity> resultList = materialRepository.commonQueryByMultiParam(kindManageParams, filterTerms, new FilterOrder("createTime", FilterKeyword.DESC));
            integrationData(resultList);
            EasyPoiUtils.exportExcel(resultList, "物料类型", "物料类型报表", MaterialKindManageEntity.class, "物料类型.xls", response);
        } catch (Exception ex) {
            log.error("[物料类型管理]查询异常：{}", ex);
        }
    }

    /**
     * 数据整合
     *
     * @param resultList 结果集
     */
    private void integrationData(List<MaterialKindManageEntity> resultList) {
        Map<String, String> consumerCacheMap = getConsumerCacheMapList();
        resultList.forEach(materialKind -> {
            String consumerName = consumerCacheMap.get((materialKind.getConsumerId().toString()));
            materialKind.setConsumerName(consumerName);
            if (CodeHelper.isNotNullOrEmpty(materialKind.getUnit())) {
                materialKind.setUnit(this.getUnitName(materialKind.getUnit()));
            }
        });
    }

    /**
     * 查询物料类型详情信息
     *
     * @param requestData 查询参数
     * @return 物料类型详情
     */
    public ResponseData getMaterialKindDetail(RequestData<MaterialKindManageEntity> requestData) {
        MaterialKindManageEntity materialKindParams = requestData.getBody();
        if (CodeHelper.isNull(materialKindParams.getProductKindId())) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {
            return CommonDataUtils.responseSuccess(materialRepository.getMaterialKindById(materialKindParams.getProductKindId()));
        } catch (Exception ex) {
            log.error("[查询物料类型详情]异常：{}", ex);
            return CommonDataUtils.responseFailure();
        }
    }

    /**
     * 保存物料类型信息
     *
     * @param requestData 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData saveMaterialKindManage(RequestData<MaterialKindManageEntity> requestData) {
        MaterialKindManageEntity materialKindParams = requestData.getBody();
        if (CodeHelper.isNullOrEmpty(materialKindParams.getProductKindName()) || CodeHelper.isNull(materialKindParams.getConsumerId()) || CodeHelper.isNullOrEmpty(materialKindParams.getSellingPrice()) || CodeHelper.isNull(materialKindParams.getUnit())) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {
            Integer consumerId = materialKindParams.getConsumerId();
            ConsumerManageRecordEntity detail = consumerRepository.getByConsumerId(consumerId);
            if (CodeHelper.isNull(detail)) {
                return CommonDataUtils.responseFailure("不存在的厂商！");
            }
            if (CodeHelper.isNotNull(materialKindParams.getProductKindId())) {
                materialKindParams.setUpdateTime(CommonDataUtils.getFormatDateString(new Date()));
            } else {
                materialKindParams.setCreateTime(CommonDataUtils.getFormatDateString(new Date()));
            }
            MaterialKindManageEntity kindRecord = materialRepository.save(materialKindParams);
            redisService.hset(REDIS_CACHE_KIND_KEY, String.valueOf(kindRecord.getProductKindId()), kindRecord.getProductKindName());
            redisService.hset(REDIS_CACHE_KIND_INFO_KEY, String.valueOf(kindRecord.getProductKindId()), JSON.toJSONString(kindRecord));
            return CommonDataUtils.responseSuccess(kindRecord);
        } catch (Exception ex) {
            log.error("[保存物料类型]出现异常：{}", ex);
            throw ex;
        }
    }

    /**
     * 删除物料类型信息
     *
     * @param requestData 请求参数
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseData deleteMaterialKind(RequestData<MaterialKindManageEntity> requestData) {
        MaterialKindManageEntity materialKindParams = requestData.getBody();
        if (CodeHelper.isNull(materialKindParams)) {
            return CommonDataUtils.responseFailure(ERROR_PARAMS);
        }
        try {
            Integer productKindId = materialKindParams.getProductKindId();
            WarehouseInventoryManageEntity checkExists = manageRepository.getByProductKindId(productKindId);
            if (CodeHelper.isNotNull(checkExists)) {
                return CommonDataUtils.responseFailure("库存中存在该物料的信息，请勿删除！");
            }
            materialRepository.deleteById(productKindId);
            redisService.hdel(REDIS_CACHE_CONSUMER_KEY, String.valueOf(productKindId));
            redisService.hdel(REDIS_CACHE_KIND_INFO_KEY, String.valueOf(productKindId));
            return CommonDataUtils.responseSuccess();
        } catch (Exception ex) {
            log.error("[删除物料类型]出现异常：{}", ex);
            throw ex;
        }
    }

    /**
     * 物料类型 key-value
     *
     * @return 物料类型
     */
    public ResponseData getMaterialKindListByType() {
        try {
            return CommonDataUtils.responseSuccess(getMaterialKindCache());
        } catch (Exception ex) {
            log.error("[获取物料类型缓存信息]出错:{}", ex);
        }
        return CommonDataUtils.errorPageResponse();
    }

    /**
     * 拼接查询参数
     *
     * @param kindManageParams 前端查询入参
     * @param filterTerms      过滤条件
     */
    private void splicingParameter(MaterialKindManageEntity kindManageParams, List<FilterTerm> filterTerms) {
        if (CodeHelper.isNotNullOrEmpty(kindManageParams.getConsumerName())) {
            List<String> consumerIdList = new ArrayList<>();
            Map<String, String> consumerCacheMap = getConsumerCacheMapList();
            consumerCacheMap.forEach((consumerId, consumerName) -> {
                if (consumerName.contains(kindManageParams.getConsumerName())) {
                    consumerIdList.add(consumerId);
                }
            });
            kindManageParams.setConsumerIds(String.join(",", consumerIdList));
            filterTerms.add(new FilterTerm("consumerName", FilterKeyword.IN));
        }
        if (CodeHelper.isNotNull(kindManageParams.getProductKindName())) {
            filterTerms.add(new FilterTerm("productKindName", FilterKeyword.EQ));
        }
    }

    /**
     * 获取物料类型信息
     *
     * @return 缓存
     */
    private Map<String, String> getMaterialKindCache() {
        if (!redisService.exists(REDIS_CACHE_KIND_KEY)) {
            baseCacheService.refreshBaseCache(REDIS_CACHE_KIND_KEY);
        }
        return redisService.hgetall(REDIS_CACHE_KIND_KEY);
    }

    /**
     * 获取客户缓存信息
     *
     * @return 缓存
     */
    private Map<String, String> getConsumerCacheMapList() {
        Map<String, String> consumerCacheMap = redisService.hgetall(CommonConstants.REDIS_CACHE_CONSUMER_KEY);
        if (CodeHelper.isNullOrEmpty(consumerCacheMap)) {
            baseCacheService.refreshBaseCache(REDIS_CACHE_CONSUMER_KEY);
            consumerCacheMap = redisService.hgetall(CommonConstants.REDIS_CACHE_CONSUMER_KEY);
        }
        return consumerCacheMap;
    }

    /**
     * 单位
     *
     * @return 单位
     */
    private String getUnitName(String key) {
        Map<String, String> unitMap = new HashMap<>(4);
        unitMap.put("0", "块");
        unitMap.put("1", "个");
        unitMap.put("2", "米");
        unitMap.put("3", "平方");
        return unitMap.get(key);
    }
}
