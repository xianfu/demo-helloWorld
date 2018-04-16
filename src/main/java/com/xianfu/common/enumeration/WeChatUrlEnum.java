package com.xianfu.common.enumeration;

/**
 * @author Created by xianfuWang
 * @version 2018/4/16.
 */
public enum WeChatUrlEnum {

    GET_ACCESS_TOKEN_URL("获取access_token","https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s");

    private String desc;
    private String url;

    public String getDesc() {
        return desc;
    }

    public String getUrl() {
        return url;
    }

    WeChatUrlEnum(String desc, String url) {
        this.desc = desc;
        this.url = url;
    }
}
