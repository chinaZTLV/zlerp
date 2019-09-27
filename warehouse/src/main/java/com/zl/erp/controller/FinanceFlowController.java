package com.zl.erp.controller;

import com.zl.erp.common.BaseController;
import com.zl.erp.common.PageMessage;
import com.zl.erp.common.RequestData;
import com.zl.erp.common.RequestDataPage;
import com.zl.erp.entity.FinanceFlowEntity;
import com.zl.erp.service.FinanceFlowService;
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
 * @Description: 财务流水管理
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Api(value = "财务流水管理")
@Slf4j
@RestController
@RequestMapping("/finance")
public class FinanceFlowController extends BaseController {

    private final FinanceFlowService flowService;

    @Autowired
    public FinanceFlowController(FinanceFlowService flowService) {
        this.flowService = flowService;
    }

    /**
     * 查询财务流水管理分页列表
     *
     * @param requestPage 请求入参
     * @return 财务流水管理
     */
    @ApiOperation(value = "查询财务流水管理分页列表")
    @PostMapping("/queryFinanceFlowForPage")
    public PageMessage queryFinanceFlowForPage(@RequestBody RequestDataPage<FinanceFlowEntity> requestPage) {
        return convert(requestPage.getHeader(), flowService.queryFinanceFlowForPage(requestPage));
    }

    /**
     * 导出财务流水管理
     *
     * @param requestData 请求入参
     */
    @ApiOperation(value = "导出财务流水管理")
    @PostMapping("/exportFinanceFlowList")
    public void exportFinanceFlowList(@RequestBody RequestData<FinanceFlowEntity> requestData, HttpServletResponse response) {
        flowService.exportFinanceFlowList(requestData, response);
    }
}
