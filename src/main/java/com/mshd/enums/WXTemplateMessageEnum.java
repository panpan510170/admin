package com.mshd.enums;

/**
 * Created by Pangaofeng on 2018/11/16
 */
public enum WXTemplateMessageEnum {
    createOrder("下单", "12323"),
    nopayOrderRemind("订单待支付提醒", "q7vWvc_D1nv6p5jRXwG3Cu0QiQwDAx-WoNLWVsmpfmI");

    private String message;
    private String templateId;

    private WXTemplateMessageEnum(String message, String templateId) {
        this.message = message;
        this.templateId = templateId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
