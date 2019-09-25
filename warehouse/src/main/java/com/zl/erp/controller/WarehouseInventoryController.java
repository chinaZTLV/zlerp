package com.zl.erp.controller;

import com.zl.erp.common.*;
import com.zl.erp.entity.PurchaseSellingOrderRecordEntity;
import com.zl.erp.entity.WarehouseInventoryEntity;
import com.zl.erp.service.WarehouseInventoryService;
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
 * @Description: 库存管理
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Api(value = "库存管理")
@Slf4j
@RestController
@RequestMapping("/inventory")
public class WarehouseInventoryController extends BaseController {

    private final WarehouseInventoryService inventoryService;

    @Autowired
    public WarehouseInventoryController(WarehouseInventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * 查询库存管理分页列表
     *
     * @param requestPage 请求入参
     * @return 库存管理分页列表
     */
    @ApiOperation(value = "查询库存管理分页列表")
    @PostMapping("/queryWarehouseInventoryForPage")
    public PageMessage queryWarehouseInventoryForPage(@RequestBody RequestDataPage<WarehouseInventoryEntity> requestPage) {
        return convert(requestPage.getHeader(), inventoryService.queryWarehouseInventoryForPage(requestPage));
    }

    /**
     * 导出库存管理
     *
     * @param requestData 请求入参
     */
    @ApiOperation(value = "导出库存管理")
    @PostMapping("/exportWarehouseInventory")
    public void exportWarehouseInventory(@RequestBody RequestData<WarehouseInventoryEntity> requestData, HttpServletResponse response) {
        inventoryService.exportWarehouseInventory(requestData, response);
    }

    /**
     * 查看库存详情信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "查看物料类型详情信息")
    @PostMapping("/getWarehouseInventoryDetail")
    public Message getWarehouseInventoryDetail(@RequestBody RequestData<WarehouseInventoryEntity> requestData) {
        return convert(requestData.getHeader(), inventoryService.getWarehouseInventoryDetail(requestData));
    }

    /**
     * 下单时获取订单金额信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "下单时获取订单金额信息")
    @PostMapping("/getOrderAmountInfo")
    public Message getOrderAmountInfo(@RequestBody RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        return convert(requestData.getHeader(), inventoryService.getOrderAmountInfo(requestData));
    }

    /**
     * 下单
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "下单")
    @PostMapping("/placingAnOrder")
    public Message placingAnOrder(@RequestBody RequestData<PurchaseSellingOrderRecordEntity> requestData) {
        try {
            return convert(requestData.getHeader(), inventoryService.placingAnOrder(requestData));
        } catch (Exception ex) {
            return convert(requestData.getHeader(), CommonDataUtils.responseFailure());
        }
    }
}
