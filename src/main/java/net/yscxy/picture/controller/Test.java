package net.yscxy.picture.controller;

import io.swagger.annotations.Api;
import net.yscxy.picture.config.ServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author WangFuKun
 * @create 2020/10/24 8:50
 */

@Api(tags = "测试接口")
@RestController
public class Test {
    @Autowired
    private ServerConfig serverConfig;
    @PostMapping("/test")
    public void imageUpload() {
        System.out.println(serverConfig.getUrl());
    }
}