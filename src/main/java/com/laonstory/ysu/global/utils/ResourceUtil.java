package com.laonstory.ysu.global.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Component
public class ResourceUtil {


    private static String PATH;

    @Value("${app.filePath}")
    public void setUploadPath(String path) {
        PATH = path;
    }

    public static void saveFile(MultipartFile file, String location) throws IOException {
        log.info(PATH);
        File targetFile = new File(PATH + location);

        if(!targetFile.isDirectory()) {
            targetFile.mkdirs();
        }

        file.transferTo(targetFile);
    }

    public static void deleteFile(String filePath) {
        File file = new File(PATH + filePath);

        if (file.exists()) {
            file.delete();
        }
    }

}
