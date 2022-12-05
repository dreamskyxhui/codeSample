package com.share.codesample.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import org.apache.commons.io.IOUtils;

/**
 * Created by lz on 2018/10/24.
 */
@Slf4j
public class FileUtils {

    /**
     * 读文件
     * @param fileName
     * @return String
     * @throws IOException if IO error
     */
    public static String readFile(String fileName) throws IOException {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in = new FileInputStream(file);
        in.read(filecontent);
        in.close();
        return new String(filecontent, encoding);
    }

    /**
     * 写文件
     * @param fileName
     * @param data
     * @throws IOException if IO error
     */
    public static void writeFile(String fileName, String data) throws IOException {
        File file = new File(fileName);
        //if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(file.getName(),true);
        fileWritter.write(data);
        fileWritter.close();
    }

    /**
     * 读入jar内部文件
     * @param fileName
     * @return
     */
    public String read(String fileName) {
        // 在jar内读入配置文件必须以流的方式
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);
        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String context = writer.toString();
        return context;
    }

    /**
     * 获取文件尺寸
     * @param file
     * @return
     */
    public static String getFileSize(File file) {
        String size = "";
        if (file.exists() && file.isFile()) {
            long fileS = file.length();
            DecimalFormat df = new DecimalFormat("#.00");
            if (fileS < 1024) {
                size = df.format((double) fileS) + "BT";
            } else if (fileS < 1048576) {
                size = df.format((double) fileS / 1024) + "KB";
            } else if (fileS < 1073741824) {
                size = df.format((double) fileS / 1048576) + "MB";
            } else {
                size = df.format((double) fileS / 1073741824) + "GB";
            }
        } else if (file.exists() && file.isDirectory()) {
            size = "";
        } else {
            size = "0BT";
        }
        return size;
    }
}
