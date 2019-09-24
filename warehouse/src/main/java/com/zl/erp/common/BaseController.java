package com.zl.erp.common;


/**
 * @Description: base
 * @Author: zhutao
 * @Date: 2019/9/18
 */
public abstract class BaseController {

    /**
     * 转换出参信息
     *
     * @param header       响应头
     * @param responseData 响应体
     * @return 结果集
     */
    public Message convert(Header header, ResponseData responseData) {
        Message message = new Message();
        header.setResultCode(responseData.getResultCode());
        header.setResultDesc(responseData.getResultDesc());
        message.setHeader(header);
        message.setBody(responseData.getData());
        return message;
    }

    /**
     * 转换出参信息
     *
     * @param header           响应头
     * @param responseDataPage 响应体
     * @return 结果集
     */
    public PageMessage convert(Header header, ResponseDataPage responseDataPage) {
        PageMessage message = new PageMessage();
        header.setResultCode(responseDataPage.getResultCode());
        header.setResultDesc(responseDataPage.getResultDesc());
        message.setHeader(header);
        message.setBody(responseDataPage.getData());
        message.setPageIndex(responseDataPage.getPageIndex());
        message.setPageSize(responseDataPage.getPageSize());
        message.setTotalCount(responseDataPage.getTotalCount());
        message.setTotalPage(responseDataPage.getTotalPage());
        return message;
    }
}
