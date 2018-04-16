package com.xianfu.timejob;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xianfu.common.enumeration.WeChatUrlEnum;
import com.xianfu.common.util.HttpUtils;
import com.xianfu.pojo.PO.WeChatPlatformPO;
import com.xianfu.service.WeChatPlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Created by xianfuWang
 * @version 2018/4/16.
 */
public class UpdateWeChatInfo {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateWeChatInfo.class);

    @Autowired
    private WeChatPlatformService weChatPlatformService;
    //两个小时更新一次
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void updateAccessToken() {
        LOGGER.info("开始更新所有的accessToken：");
        List<WeChatPlatformPO> weChatPlatformPOs = weChatPlatformService.listWeChatPlatform();
        if (CollectionUtils.isEmpty(weChatPlatformPOs)) {
            LOGGER.info("没有查到需要更新的微信配置，后续操作不执行");
            return;
        }
        for (WeChatPlatformPO weChatPlatform : weChatPlatformPOs) {
            String url = String.format(WeChatUrlEnum.GET_ACCESS_TOKEN_URL.getUrl(), weChatPlatform.getAccessToken(), weChatPlatform.getAppSecret());
            LOGGER.info("更新appid={}的微信配置,url:{}", weChatPlatform.getAppId(), url);

            String result = HttpUtils.doGet(url, null);
            LOGGER.info("微信返回：{}", result);

            JSONObject resultJson = JSON.parseObject(result);
            if (!StringUtils.isEmpty(resultJson.getString("errmsg"))) {
                LOGGER.error("刷新access_token失败，微信返回值：{}", resultJson.getString("errmsg"));
            }

            String accessToken = resultJson.getString("access_token");
            weChatPlatformService.updateWeChatAccessTokenByAppId(weChatPlatform.getAppId(), accessToken);
        }
    }
}
