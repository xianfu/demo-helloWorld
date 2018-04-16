package com.xianfu.mapper;


import com.xianfu.pojo.PO.WeChatPlatformPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Created by xianfuWpang
 * @version 2018/4/13.
 */
@Mapper
public interface WeChatPlatformMapper {
    /**
     * 获取所有公众平台
     *
     * @return
     */
    List<WeChatPlatformPO> listWeChatPlatform();

    WeChatPlatformPO getWeChatInfoByAppId(@Param("appId") String appId);

    Integer updateWeChatAccessTokenByAppId(@Param("appId") String appId, @Param("accessToken") String accessToken);
}
