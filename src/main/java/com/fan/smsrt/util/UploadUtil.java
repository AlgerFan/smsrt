package com.fan.smsrt.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 阿杰
 * @create 2018-07-19 21:32
 * @Description: 上传文件
 */
public class UploadUtil {
    //文件写入
    public boolean saveFile(MultipartFile file, String filePath) {
        try {
            if (!file.isEmpty()) {
                File saveFile = new File("." + filePath);
                FileOutputStream outputStream = new FileOutputStream(saveFile);
                BufferedOutputStream out = new BufferedOutputStream(outputStream);
                out.write(file.getBytes());
                out.flush();
                out.close();
                outputStream.close();
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除文件
    public boolean deleteFile(String url) {
        File file = new File("." + url);
        if (file.exists() && file.exists()) {
            return file.delete();
        }
        return false;
    }

}
