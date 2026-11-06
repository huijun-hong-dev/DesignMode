package com.designmode.examples.SimpleFactory;

import com.designmode.examples.SimpleFactory.models.Alipay;
import com.designmode.examples.SimpleFactory.models.CardPay;
import com.designmode.examples.SimpleFactory.models.WechatPay;

public class PaymentFactory {
    public static Payment create(String payType) {
        switch (payType) {
            case "alipay":
                return new Alipay();
            case "wechat":
                return new WechatPay();
            case "card":
                return new CardPay();
            default:
                throw new IllegalArgumentException("不支持的支付方式: " + payType);
        }
    }
}
