package com.zl.erp.controller;

import com.zl.erp.common.*;
import com.zl.erp.entity.ConsumerManageRecordEntity;
import com.zl.erp.service.ConsumerManageService;
import com.zl.erp.utils.CommonDataUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 客户管理
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Api(value = "客户管理")
@Slf4j
@RestController
@RequestMapping("/consumer")
public class ConsumerManageController extends BaseController {

    private final ConsumerManageService consumerManageService;

    @Autowired
    public ConsumerManageController(ConsumerManageService consumerManageService) {
        this.consumerManageService = consumerManageService;
    }

    /**
     * 查询客户分页列表
     *
     * @param requestPage 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "查询客户分页列表")
    @PostMapping("/queryConsumerManageForPage")
    public PageMessage queryConsumerManageForPage(@RequestBody RequestDataPage<ConsumerManageRecordEntity> requestPage) {
        return convert(requestPage.getHeader(), consumerManageService.queryConsumerManageForPage(requestPage));
    }

    /**
     * 导出客户信息
     *
     * @param requestPage 请求入参
     */
    @ApiOperation(value = "导出客户信息")
    @PostMapping("/exportConsumerManageRecord")
    public void exportConsumerManageRecord(@RequestBody RequestData<ConsumerManageRecordEntity> requestPage, HttpServletResponse response) {
        consumerManageService.exportConsumerManageRecord(requestPage, response);
    }

    /**
     * 新增客户信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "新增客户信息")
    @PostMapping("/save")
    public Message saveConsumerManageRecord(@RequestBody RequestData<ConsumerManageRecordEntity> requestData) {
        try {
            return convert(requestData.getHeader(), consumerManageService.saveConsumerManageRecord(requestData));
        } catch (Exception ex) {
            return convert(requestData.getHeader(), CommonDataUtils.responseFailure());
        }
    }

    /**
     * 删除客户信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "删除客户信息", notes = "检查是否存在物料类型")
    @PostMapping("/deleteConsumerById")
    public Message deleteConsumerById(@RequestBody RequestData<ConsumerManageRecordEntity> requestData) {
        try {
            return convert(requestData.getHeader(), consumerManageService.deleteConsumerById(requestData));
        } catch (Exception ex) {
            return convert(requestData.getHeader(), CommonDataUtils.responseFailure());
        }
    }

    /**
     * 查看客户详情信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "查看客户详情信息")
    @PostMapping("/getConsumerManageDetail")
    public Message getConsumerManageDetail(@RequestBody RequestData<ConsumerManageRecordEntity> requestData) {
        return convert(requestData.getHeader(), consumerManageService.getConsumerManageDetail(requestData));
    }

    /**
     * 厂方信息
     *
     * @return 厂方信息
     */
    @ApiOperation(value = "获取下拉列表", notes = "从redis中读取")
    @PostMapping("/getConsumerListByType")
    public Message getConsumerListByType() {
        return convert(new Header(), consumerManageService.getConsumerListByType());
    }

}
