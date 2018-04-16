package com.xianfu.controller.wechat;

import com.alibaba.fastjson.JSON;
import com.xianfu.common.util.HttpUtils;
import com.xianfu.pojo.PO.WeChatPlatformPO;
import com.xianfu.pojo.VO.WeChatPlatformVO;
import com.xianfu.service.WeChatPlatformService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by xianfuWang
 * @version 2018/4/13.
 */
@Controller
@RequestMapping(value = "/weChat")
public class ManagementController {

    @Autowired
    private WeChatPlatformService weChatPlatformService;

    private static final String WE_CHAT_LIST = "/wechat/list";

    @RequestMapping(value = "/list")
    public String testGet(HttpServletRequest request) {
        List<WeChatPlatformPO> weChatPlatformPOs = weChatPlatformService.listWeChatPlatform();
        request.setAttribute("platforms", weChatPlatformPOs);
        return WE_CHAT_LIST;
    }

    @ResponseBody
    @RequestMapping(value = "/getPlatforms", method = RequestMethod.POST)
    public String getPlatforms(@RequestBody String request) {
        List<WeChatPlatformPO> weChatPlatformPOs = weChatPlatformService.listWeChatPlatform();

        List<WeChatPlatformVO> weChatPlatformVOs = new ArrayList<>();
        for (WeChatPlatformPO weChatPlatform : weChatPlatformPOs) {
            WeChatPlatformVO weChatPlatformVO = new WeChatPlatformVO();
            BeanUtils.copyProperties(weChatPlatform, weChatPlatformVO);
            weChatPlatformVOs.add(weChatPlatformVO);
        }
        return JSON.toJSONString(weChatPlatformVOs);
    }
}
