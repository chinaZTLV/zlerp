package com.zl.erp.controller;

import com.zl.erp.common.BaseController;
import com.zl.erp.common.PageMessage;
import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.entity.PurchaseSellingRecordEntity;
import com.zl.erp.service.PurchaseSellingService;
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
 * @Description: 进售货记录管理
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Api(value = "进售货记录管理")
@Slf4j
@RestController
@RequestMapping("/purchaseSelling")
public class PurchaseSellingController extends BaseController {

    private final PurchaseSellingService purchaseSellingService;

    @Autowired
    public PurchaseSellingController(PurchaseSellingService purchaseSellingService) {
        this.purchaseSellingService = purchaseSellingService;
    }


    /**
     * 进售货记录管理分页列表
     *
     * @param requestPage 请求入参
     * @return 料类型信息
     */
    @ApiOperation(value = "进售货记录管理分页列表")
    @PostMapping("/queryPurchaseSellingForPage")
    public PageMessage queryPurchaseSellingForPage(@RequestBody RequestDataPage<PurchaseSellingRecordEntity> requestPage) {
        return convert(requestPage.getHeader(), purchaseSellingService.queryPurchaseSellingForPage(requestPage));
    }

    /**
     * 进售货记录管理
     *
     * @param requestData 请求入参
     */
    @ApiOperation(value = "导出进售货记录管理")
    @PostMapping("/exportPurchaseSellingRecord")
    public void exportPurchaseSellingRecord(@RequestBody RequestData<PurchaseSellingRecordEntity> requestData, HttpServletResponse response) {
        purchaseSellingService.exportPurchaseSellingRecord(requestData, response);
    }
}
