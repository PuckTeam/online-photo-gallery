package net.yscxy.picture.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author WangFuKun
 * @create 2020/8/26 18:02
 */
public class FileUtil {
    public static boolean upload(MultipartFile file, String localPath, String fileName) {
        File dest = new File(localPath + "/" + fileName);
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getJarPath() {
        ApplicationHome ah = new ApplicationHome(FileUtil.class);
        File file = ah.getSource();
        return file.getParentFile().toString();
    }
    public static boolean MkDir() {
        String basePathString = FileUtil.getJarPath();
        File file = new File(basePathString + "/" + "file");
        try {
            if (!file.exists()) {
                FileUtils.forceMkdir(file);
                return true;
            } else {
                System.out.println("该文件夹已经存在了！");
                return true;
            }
        } catch (Exception e) {
            return false;
        }

    }
}