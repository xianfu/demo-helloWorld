package com.xianfu.controller.wechat;

import com.xianfu.common.enumeration.WeChatUrlEnum;
import com.xianfu.common.util.HttpUtils;
import com.xianfu.common.util.WeChatUtils;
import com.xianfu.controller.BaseController;
import com.xianfu.pojo.PO.WeChatPlatformPO;
import com.xianfu.service.WeChatPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @author Created by xianfuWang
 * @version 2018/4/16.
 */
@Controller
@RequestMapping(value = "/weChat")
public class ExchangeController extends BaseController {

    @Autowired
    private WeChatPlatformService weChatPlatformService;

    @RequestMapping(value = "/auth")
    public void auth(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        LOGGER.info("收到微信验证请求:");
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        LOGGER.info("signature={}", signature);
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        LOGGER.info("timestamp={}", timestamp);
        // 随机数
        String nonce = request.getParameter("nonce");
        LOGGER.info("nonce={}", nonce);
        // 随机字符串
        String echostr = request.getParameter("echostr");
        LOGGER.info("echostr={}", echostr);
        //获取本地配置信息
        WeChatPlatformPO weChatPlatformPO = weChatPlatformService.getWeChatInfoByAppId(null);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            String token = weChatPlatformPO.getToken();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (WeChatUtils.checkSignature(signature, token, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getAccessToken")
    public String getAccessToken(HttpServletRequest request) {
        String appId = request.getParameter("app_id");
        String appSecret = request.getParameter("app_secret");
        String url = String.format(WeChatUrlEnum.GET_ACCESS_TOKEN_URL.getUrl(), appId, appSecret);
        String result = HttpUtils.doGet(url, null);
        return result;
    }
}
