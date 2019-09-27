package com.zl.erp.utils;

import com.zl.erp.common.ResponseData;
import com.zl.erp.common.ResponseDataPage;
import com.zl.erp.constants.CommonConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Description: 公共数据处理类
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public class CommonDataUtils {

    /**
     * 响应成功
     *
     * @return 响应信息
     */
    public static ResponseData responseSuccess() {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_SUCCESS);
        responseData.setResultDesc(CommonConstants.RESPONSE_SUCCESS_MESSAGE);
        return responseData;
    }

    /**
     * 响应成功
     *
     * @param data 信息
     * @param <T>  T
     * @return 响应信息
     */
    public static <T> ResponseData responseSuccess(T data) {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_SUCCESS);
        responseData.setResultDesc(CommonConstants.RESPONSE_SUCCESS_MESSAGE);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 响应成功
     *
     * @param responseMessage 信息
     * @return 响应信息
     */
    public static ResponseData responseSuccess(String responseMessage) {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_SUCCESS);
        responseData.setResultDesc(responseMessage);
        return responseData;
    }

    /**
     * 响应失败
     *
     * @return 响应信息
     */
    public static ResponseData responseFailure() {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_FAILURE);
        responseData.setResultDesc(CommonConstants.RESPONSE_FAILURE_MESSAGE);
        return responseData;
    }

    /**
     * 响应失败
     *
     * @param data 信息
     * @param <T>  T
     * @return 响应信息
     */
    public static <T> ResponseData responseFailure(T data) {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_FAILURE);
        responseData.setResultDesc(CommonConstants.RESPONSE_FAILURE_MESSAGE);
        responseData.setData(data);
        return responseData;
    }

    /**
     * 响应失败
     *
     * @param responseMessage 信息
     * @return 响应信息
     */
    public static ResponseData responseFailure(String responseMessage) {
        ResponseData responseData = new ResponseData();
        responseData.setResultCode(CommonConstants.RESPONSE_FAILURE);
        responseData.setResultDesc(responseMessage);
        return responseData;
    }

    /**
     * 请求错误相应
     *
     * @return 错误实体信息
     */
    public static ResponseDataPage errorPageResponse() {
        ResponseDataPage pageEntity = new ResponseDataPage();
        pageEntity.setResultCode(CommonConstants.RESPONSE_FAILURE);
        pageEntity.setResultDesc(CommonConstants.RESPONSE_FAILURE_MESSAGE);
        return pageEntity;
    }

    /**
     * 请求错误相应
     *
     * @return 错误实体信息
     */
    public static ResponseDataPage successPageResponse() {
        ResponseDataPage pageEntity = new ResponseDataPage();
        pageEntity.setResultCode(CommonConstants.RESPONSE_SUCCESS);
        pageEntity.setResultDesc(CommonConstants.RESPONSE_SUCCESS_MESSAGE);
        return pageEntity;
    }

    /**
     * 时间格式化
     *
     * @return 时间串
     */
    public static String getFormatDateString(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 保留两位小数
     *
     * @param bigDecimal 数值
     * @return 两位小数值
     */
    public static String formatToString(BigDecimal bigDecimal) {
        DecimalFormat format = new DecimalFormat("#0.00");
        format.setRoundingMode(RoundingMode.FLOOR);
        return format.format(bigDecimal);
    }

    /**
     * 生成uuid
     *
     * @return uuid
     */
    public static String getUUID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
        String random = String.format("%03d", new Random().nextInt(1000));
        return sdf.format(new Date()) + random;
    }

    /**
     * 支付扣款-金额转换为负数入库
     *
     * @param amount 扣款金额
     * @return -扣款金额
     */
    public static BigDecimal decimalToMinus(BigDecimal amount) {
        return new BigDecimal("-" + amount.toString());
    }
}
