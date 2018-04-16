package com.xianfu.service;

import com.xianfu.pojo.PO.WeChatPlatformPO;

import java.util.List;

/**
 * @author Created by xianfuWang
 * @version 2018/4/12.
 */
public interface WeChatPlatformService {
    List<WeChatPlatformPO> listWeChatPlatform();
    WeChatPlatformPO getWeChatInfoByAppId(String appId);
    Integer updateWeChatAccessTokenByAppId(String appId, String accessToken);
}
