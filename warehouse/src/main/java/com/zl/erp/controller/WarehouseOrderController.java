package com.zl.erp.controller;

import com.zl.erp.common.*;
import com.zl.erp.entity.WarehouseInventoryEntity;
import com.zl.erp.entity.WarehouseOrderEntity;
import com.zl.erp.service.WarehouseOrderService;
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
 * @Description: 订单信息
 * @Author: zhutao
 * @Date: 2019/9/26
 */
@Api(value = "订单管理")
@Slf4j
@RestController
@RequestMapping("/order")
public class WarehouseOrderController extends BaseController {

    private final WarehouseOrderService orderService;

    @Autowired
    public WarehouseOrderController(WarehouseOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询订单管理分页列表
     *
     * @param requestPage 请求入参
     * @return 查询订单管理分页列表
     */
    @ApiOperation(value = "查询订单管理分页列表")
    @PostMapping("/queryWarehouseInventoryForPage")
    public PageMessage queryWarehouseOrderForPage(@RequestBody RequestDataPage<WarehouseOrderEntity> requestPage) {
        return convert(requestPage.getHeader(), orderService.queryWarehouseOrderForPage(requestPage));
    }

    /**
     * 导出订单管理分页列表
     *
     * @param requestData 请求入参
     */
    @ApiOperation(value = "导出订单管理分页列表")
    @PostMapping("/exportWarehouseOrder")
    public void exportWarehouseOrder(@RequestBody RequestData<WarehouseOrderEntity> requestData, HttpServletResponse response) {
        orderService.exportWarehouseOrder(requestData, response);
    }

    /**
     * 查看订单管理详情信息
     *
     * @param requestData 请求入参
     * @return 订单管理详情
     */
    @ApiOperation(value = "查看订单管理详情信息")
    @PostMapping("/getWarehouseOrderDetail")
    public Message getWarehouseOrderDetail(@RequestBody RequestData<WarehouseOrderEntity> requestData) {
        return convert(requestData.getHeader(), orderService.getWarehouseOrderDetail(requestData));
    }

    /**
     * 删除订单
     *
     * @param requestData 请求入参
     * @return 执行结果
     */
    @ApiOperation(value = "删除订单")
    @PostMapping("/deleteWarehouseOrderById")
    public Message deleteWarehouseOrderById(@RequestBody RequestData<WarehouseOrderEntity> requestData) {
        return convert(requestData.getHeader(), orderService.deleteWarehouseOrderById(requestData));
    }

    /**
     * 发货
     *
     * @param requestData 请求入参
     * @return 执行结果
     */
    @ApiOperation(value = "发货")
    @PostMapping("/doDelivered")
    public Message doDelivered(@RequestBody RequestData<WarehouseOrderEntity> requestData) {
        return convert(requestData.getHeader(), orderService.doDelivered(requestData));
    }

    /**
     * 已支付
     *
     * @param requestData 请求入参
     * @return 执行结果
     */
    @ApiOperation(value = "已支付")
    @PostMapping("/doPayment")
    public Message doPayment(@RequestBody RequestData<WarehouseOrderEntity> requestData) {
        return convert(requestData.getHeader(), orderService.doPayment(requestData));
    }
}
