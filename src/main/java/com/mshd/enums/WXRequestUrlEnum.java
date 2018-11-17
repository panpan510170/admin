package com.mshd.enums;

/**
 * Created by Pangaofeng on 2018/11/16
 */
public enum WXRequestUrlEnum {
    getToken("获取access_token", "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential"),
    sendMessage("推送模板消息", "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send");

    private String message;
    private String url;

    private WXRequestUrlEnum(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
