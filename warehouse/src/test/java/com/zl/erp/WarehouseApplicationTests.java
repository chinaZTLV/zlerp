//package com.zl.erp;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.zl.erp.common.Header;
//import com.zl.erp.common.Message;
//import com.zl.erp.constants.CommonConstants;
//import com.zl.erp.entity.ConsumerManageRecordEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.net.URL;
//
///**
// * @Description: 单元测试启动类
// * @Author: zhutao
// * @Date: 2019/9/29
// */
//@RunWith(SpringRunner.class)
//@Slf4j
//@SpringBootTest(classes = WarehouseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class WarehouseApplicationTests {
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    private URL base;
//
//    private HttpHeaders httpHeaders = new HttpHeaders();
//
//    @Autowired
//    private WebApplicationContext wac;
//
//    private MockMvc mockMvc;
//
//    @LocalServerPort
//    private int port;
//
//    @Before
//    public void setUp() throws Exception {
//        String url = String.format("http://127.0.0.1:%d/", port);
//        log.warn("[单元测试]请求Url:{}", url);
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//        this.base = new URL(url);
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//    }
//
//    @Test
//    public void getConsumerManageDetail() {
//        ConsumerManageRecordEntity consumer = new ConsumerManageRecordEntity();
//        consumer.setConsumerId(1);
//        Message message = restTemplate.postForObject(base + "/consumer/getConsumerManageDetail", requestParams(consumer), Message.class);
//        log.warn("[单元测试]请求响应信息:{}", JSON.toJSONString(message));
//        Assert.assertEquals(message.getHeader().getResultCode(), CommonConstants.RESPONSE_SUCCESS);
//    }
//
//    @Test
//    public void manageDetail() throws Exception {
//        ConsumerManageRecordEntity consumer = new ConsumerManageRecordEntity();
//        consumer.setConsumerId(1);
//        JSONObject msg = new JSONObject();
//        msg.put("header", new Header());
//        msg.put("body", consumer);
//        String responseJson = mockMvc.perform(MockMvcRequestBuilders.
//                post(base + "/consumer/getConsumerManageDetail").
//                headers(httpHeaders).
//                content(msg.toJSONString()).
//                contentType(MediaType.APPLICATION_JSON_UTF8)).
//                andExpect(MockMvcResultMatchers.status().isOk()).
//                andReturn().
//                getResponse().
//                getContentAsString();
//        log.warn("[单元测试]请求响应信息:{}", responseJson);
//
//    }
//
//    /**
//     * 参数构造
//     *
//     * @param data 参数
//     * @param <T>  参数
//     * @return request
//     */
//    private <T> HttpEntity<String> requestParams(T data) {
//        JSONObject msg = new JSONObject();
//        msg.put("header", new Header());
//        msg.put("body", data);
//        return new HttpEntity<>(msg.toJSONString(), httpHeaders);
//    }
//}
