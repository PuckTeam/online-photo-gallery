package net.yscxy.picture.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author WangFuKun
 * @create 2020/8/26 18:02
 */
public class FileUtils {
    public static boolean upload(MultipartFile file, String localPath, String fileName) {
        File dest = new File(localPath + "/"+fileName);
        try {
            file.transferTo(dest);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}