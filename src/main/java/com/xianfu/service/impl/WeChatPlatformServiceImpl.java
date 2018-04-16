package com.xianfu.service.impl;

import com.xianfu.mapper.WeChatPlatformMapper;
import com.xianfu.pojo.PO.WeChatPlatformPO;
import com.xianfu.service.WeChatPlatformService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by xianfuWang
 * @version 2018/4/13.
 */
@Service
public class WeChatPlatformServiceImpl implements WeChatPlatformService {

    @Autowired
    private WeChatPlatformMapper weChatPlatformMapper;

    @Override
    public List<WeChatPlatformPO> listWeChatPlatform() {
        return weChatPlatformMapper.listWeChatPlatform();
    }

    @Override
    public WeChatPlatformPO getWeChatInfoByAppId(String appId) {
        Validate.notBlank(appId,"appId不能为空!!!");
        return weChatPlatformMapper.getWeChatInfoByAppId(appId);
    }

    @Override
    public Integer updateWeChatAccessTokenByAppId(String appId, String accessToken) {
        Validate.notBlank(appId,"appId不能为空!!!");
        Validate.notBlank(accessToken,"accessToken不能为空!!!");
        return weChatPlatformMapper.updateWeChatAccessTokenByAppId(appId, accessToken);
    }
}
