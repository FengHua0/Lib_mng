package com.xdc5.libmng.controller;

import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xdc5.libmng.config.AliPayConfig;
import com.xdc5.libmng.entity.Bill;
import com.xdc5.libmng.entity.Result;
import com.xdc5.libmng.service.BillService;
import com.xdc5.libmng.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class AliPayController {

    private static final String GATEWAY_URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    private static final String FORMAT = "JSON";
    private static final String CHARSET = "UTF-8";
    // 签名方式
    private static final String SIGN_TYPE = "RSA2";

    @Resource
    private AliPayConfig aliPayConfig;

    @Autowired
    BillService billService;

    @Autowired
    UserService userService;

    // totalAmount subject
    @GetMapping("/alipay/pay")
    public void pay(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse,
            @RequestParam BigDecimal totalAmount,
            @RequestParam String subject,
            @RequestParam Integer userId) throws Exception {
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setBillAmount(totalAmount);
        bill.setBillSubject(subject);
        bill.setBillId(UUID.randomUUID().toString());
        // 创建bill
        billService.addBill(bill);
        log.info("billId:{}", bill.getBillId());
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(GATEWAY_URL, aliPayConfig.getAppId(),
                aliPayConfig.getAppPrivateKey(), FORMAT, CHARSET, aliPayConfig.getAlipayPublicKey(), SIGN_TYPE);

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest(); // 发送请求的 Request类
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", bill.getBillId()); // 我们自己生成的订单编号
        bizContent.set("total_amount", bill.getBillAmount()); // 订单的总金额
        bizContent.set("subject", bill.getBillSubject()); // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY"); // 固定配置
        log.info(String.valueOf(bizContent));
        request.setBizContent(bizContent.toString());

        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }

    @PostMapping("/alipay/notify")
    public Result payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            log.info("=========支付宝异步回调========");
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }
            String outTradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign,
                    aliPayConfig.getAlipayPublicKey(), "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                log.info("交易名称: {}", params.get("subject"));
                log.info("交易状态: {}", params.get("trade_status"));
                log.info("支付宝交易凭证号: {}", params.get("trade_no"));
                log.info("商户订单号: {}", params.get("out_trade_no"));
                log.info("交易金额: {}", params.get("total_amount"));
                log.info("买家在支付宝唯一id: {}", params.get("buyer_id"));
                log.info("买家付款时间: {}", params.get("gmt_payment"));
                log.info("买家付款金额: {}", params.get("buyer_pay_amount"));
                String total_amount = params.get("buyer_pay_amount");
                if (billService.updateStatusById(outTradeNo, 1) == 0) {
                    log.info("error no:{}", outTradeNo);

                } else {
                    log.info("success update status:{}", outTradeNo);
                    int userId = billService.getUserIdByBillId(outTradeNo);
                    userService.increaseUserMoney(userId, new BigDecimal(total_amount));
                }
            }
        }
        return Result.success("success");
    }
}