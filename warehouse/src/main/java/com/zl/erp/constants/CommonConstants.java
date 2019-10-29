package com.zl.erp.constants;

/**
 * @Description: 常量类
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public class CommonConstants {

    /**
     * 响应成功码
     */
    public static final String RESPONSE_SUCCESS = "0";

    /**
     * 响应成功信息
     */
    public static final String RESPONSE_SUCCESS_MESSAGE = "SUCCESS";

    /**
     * 响应失败码
     */
    public static final String RESPONSE_FAILURE = "-1";

    /**
     * 响应失败信息
     */
    public static final String RESPONSE_FAILURE_MESSAGE = "FAILURE";

    /**
     * 用户已存在
     */
    public static final String EXISTS_CONSUMER_USER_NAME_ERROR = "用户名已存在";

    /**
     * 物料名称已存在
     */
    public static final String EXISTS_MATERIAL_NAME_ERROR = "物料名称已存在";

    /**
     * 用户已存在
     */
    public static final String ERROR_PARAMS = "参数错误";

    /**
     * 创建时间倒叙
     */
    public static final String CREATE_TIME_DESC = "createTime";

    /**
     * 倒叙
     */
    public static final String KEYWORD_ORDER_DESC = "0";

    /**
     * 正序
     */
    public static final String KEYWORD_ORDER_ASC = "1";

    /**
     * redis缓存
     */
    public static final String REDIS_CACHE_CONSUMER_KEY = "REDIS_CACHE_CONSUMER";

    /**
     * redis缓存
     */
    public static final String REDIS_CACHE_KIND_KEY = "REDIS_CACHE_KIND";

    /**
     * redis缓存
     */
    public static final String REDIS_CACHE_KIND_INFO_KEY = "REDIS_CACHE_KIND_INFO_KEY";

    /**
     * 厂方
     */
    public static final String FACTORY_TYPE = "0";

    /**
     * 无此物料类型
     */
    public static final String NOT_EXISTS_MATERIAL_KIND = "无此物料类型！";

    /**
     * 退还厂方
     */
    public static final int MANAGE_TYPE_RETURNED_TO_FACTORY = 0;

    /**
     * 进货
     */
    public static final int MANAGE_TYPE_PURCHASE = 1;

    /**
     * 售货
     */
    public static final int MANAGE_TYPE_SALES = 2;

    /**
     * 退货
     */
    public static final int MANAGE_TYPE_RETURNED_PURCHASE = 3;

    /**
     * 已下单
     */
    public static final String ORDER_PLACED = "0";

    /**
     * 已发货
     */
    public static final String ORDER_DELIVER_GOODS = "1";

    /**
     * 已付款
     */
    public static final String ORDER_PAY = "2";

    /**
     * 默认值
     */
    public static final String DEFAULT_VALUE = "0.00";

}
