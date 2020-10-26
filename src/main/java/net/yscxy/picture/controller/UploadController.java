package net.yscxy.picture.controller;

/**
 * @Author WangFuKun
 * @create 2020/8/26 17:43
 */


import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import net.yscxy.picture.config.ServerConfig;
import net.yscxy.picture.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "上传图片接口")
@RestController
public class UploadController {
    @Autowired
    private ServerConfig serverConfig;
    private String folder ="file";
    @PostMapping("/Upload")
    public Map imageUpload(MultipartFile file) {
       FileUtil.MkDir();
        String result_msg = "";//上传结果信息
        Map<String, Object> root = new HashMap<String, Object>();
        if (file == null) {
            result_msg = "图片不能为空";
        } else if (file.getSize() / 500000 > 100) {
            result_msg = "图片大小不能超过10MB";
        } else {
            String fileType = file.getContentType();
            // 要上传的目标文件存放的绝对路径
            //用src为保存绝对路径不能改名只能用原名，不用原名会导致ajax上传图片后在前端显示时出现404错误-->原因未知
//                  String localPath="F:\\IDEAProject\\imageupload\\src\\main\\resources\\static\\img";
            //windows环境
//                final String localPath = "D:\\img";
            //服务器环境
            final String localPath = FileUtil.getJarPath()+"/"+folder;
            //上传后保存的文件名(需要防止图片重名导致的文件覆盖)
            //获取文件名
            System.out.println(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            //获取文件后缀名
            /*String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = UUID.randomUUID() + suffixName;*/
            if (FileUtil.upload(file, localPath, fileName)) {
                //文件存放的相对路径(一般存放在数据库用于img标签的src)
                String relativePath = serverConfig.getUrl() +"/"+folder+"/" + fileName;
                System.out.println(relativePath);
                root.put("relativePath",relativePath);//前端根据是否存在该字段来判断上传是否成功
                result_msg = "图片上传成功";
            } else {
                result_msg = "图片上传失败";
            }
        }
        root.put("result_msg", result_msg);
        String root_json = JSON.toJSONString(root);
        System.out.println(root_json);
        return root;
    }
}
