package net.yscxy.picture;

import net.yscxy.picture.util.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class PictureApplication {

    public static void main(String[] args) {
            SpringApplication.run(PictureApplication.class, args);
    }

}
