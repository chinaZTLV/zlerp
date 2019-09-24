package com.zl.erp.controller;

import com.zl.erp.common.*;
import com.zl.erp.entity.ConsumerManageRecordEntity;
import com.zl.erp.entity.MaterialKindManageEntity;
import com.zl.erp.service.MaterialKindManageService;
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
 * @Description: 物料类型管理
 * @Author: zhutao
 * @Date: 2019/9/19
 */
@Api(value = "物料类型管理")
@Slf4j
@RestController
@RequestMapping("/materialKind")
public class MaterialKindManageController extends BaseController {

    private final MaterialKindManageService kindManageService;

    @Autowired
    public MaterialKindManageController(MaterialKindManageService kindManageService) {
        this.kindManageService = kindManageService;
    }

    /**
     * 查询物料类型信息分页列表
     *
     * @param requestPage 请求入参
     * @return 料类型信息
     */
    @ApiOperation(value = "查询物料类型信息分页列表")
    @PostMapping("/queryMaterialKindForPage")
    public PageMessage queryMaterialKindForPage(@RequestBody RequestDataPage<MaterialKindManageEntity> requestPage) {
        return convert(requestPage.getHeader(), kindManageService.queryMaterialKindForPage(requestPage));
    }

    /**
     * 获取物料信息下拉框
     *
     * @return 料类型信息
     */
    @ApiOperation(value = "获取物料信息下拉框")
    @PostMapping("/getMaterialKindListByType")
    public Message getMaterialKindListByType() {
        return convert(new Header(), kindManageService.getMaterialKindListByType());
    }

    /**
     * 导出物料类型信息
     *
     * @param requestData 请求入参
     */
    @ApiOperation(value = "导出物料类型信息")
    @PostMapping("/exportMaterialKindList")
    public void exportMaterialKindList(@RequestBody RequestData<MaterialKindManageEntity> requestData, HttpServletResponse response) {
        kindManageService.exportMaterialKindList(requestData, response);
    }

    /**
     * 新增物料类型信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "新增物料类型信息")
    @PostMapping("/save")
    public Message saveMaterialKindManage(@RequestBody RequestData<MaterialKindManageEntity> requestData) {
        try {
            return convert(requestData.getHeader(), kindManageService.saveMaterialKindManage(requestData));
        } catch (Exception ex) {
            return convert(requestData.getHeader(), CommonDataUtils.responseFailure());
        }
    }

    /**
     * 查看物料类型详情信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "查看物料类型详情信息")
    @PostMapping("/getMaterialKindDetail")
    public Message getMaterialKindDetail(@RequestBody RequestData<MaterialKindManageEntity> requestData) {
        return convert(requestData.getHeader(), kindManageService.getMaterialKindDetail(requestData));
    }

    /**
     * 删除物料类型信息
     *
     * @param requestData 请求入参
     * @return 客户信息
     */
    @ApiOperation(value = "删除物料类型信息", notes = "检查库存中是否存在该类型的信息")
    @PostMapping("/deleteMaterialKindById")
    public Message deleteMaterialKind(@RequestBody RequestData<MaterialKindManageEntity> requestData) {
        try {
            return convert(requestData.getHeader(), kindManageService.deleteMaterialKind(requestData));
        } catch (Exception ex) {
            return convert(requestData.getHeader(), CommonDataUtils.responseFailure());
        }
    }

}
