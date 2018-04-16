package com.xianfu;

import com.alibaba.fastjson.JSON;
import com.xianfu.pojo.PO.WeChatPlatformPO;
import com.xianfu.service.WeChatPlatformService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

    @Autowired
    private WeChatPlatformService weChatPlatformService;

    @Test
    public void contextLoads() {
        List<WeChatPlatformPO> weChatPlatformPOs = weChatPlatformService.listWeChatPlatform();
        System.out.println(JSON.toJSON(weChatPlatformPOs));
    }

}
