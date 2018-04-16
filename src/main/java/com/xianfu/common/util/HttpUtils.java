package com.xianfu.common.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xianfu.common.enumeration.BaseCode;
import com.xianfu.common.exception.BaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Created by xianfuWang
 * @version 2018/4/13.
 */
public class HttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    public static String doPost(String urlParam, String json) throws Exception {
        if (StringUtils.isEmpty(urlParam) || StringUtils.isEmpty(json)) {
            throw new BaseException(BaseCode.REQUEST_PARAM_IS_EMPTY);
        }
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        StringBuffer sb = new StringBuffer("");
        try {
            // 创建连接
            URL url = new URL(urlParam);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            connection.connect();

            // POST请求
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.write(json.getBytes());
            out.flush();
            out.close();

            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String lines;
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                // 断开连接
                connection.disconnect();
            }
        }
        return sb.toString();
    }

    public static String doGet(String url, Map<String, String> parms) {
        if (!url.contains("?")) {
            url += "?1=1";
        }

        StringBuffer src = new StringBuffer(url);
        if (!CollectionUtils.isEmpty(parms)) {
            for (String str : parms.keySet()) {
                src.append("&" + str + "=").append(parms.get(str));
            }
        }

        url = src.toString();

        try {
            RestTemplate template = new RestTemplate();
            //乱码问题
            template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            return template.getForObject(new URI(url), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean isSuccessResult(String result) {
        JSONObject resultJson = JSON.parseObject(result);
        return resultJson.getIntValue("code") == 200;
    }

}
