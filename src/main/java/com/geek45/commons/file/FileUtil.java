package com.geek45.commons.file;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

@Slf4j
public class FileUtil {

    /**
     * 检测是否是文件夹
     * @param file 要检测的对象
     * @return true 是文件夹  false  不是文件夹
     */
    public static boolean isFolder(@NonNull File file) {
        if (file.exists()) {
            return file.isDirectory();
        } else {
            log.info("文件不存在");
            return false;
        }
    }

    /**
     * 获取文件夹下面的所有文件
     * @param file 要获取文件的文件夹
     * @return 如果不是文件夹，就返回空，否则就返回所有文件
     */
    public static File[] getFileAll(@NonNull File file) {
        if (isFolder(file)) {
            return file.listFiles();
        }else{
            return new File[0];
        }
    }

    /**
     * 获取文件的后缀
     * @param file  要获取后缀的文件
     * @return  例如 r.txt  则返回 .txt  ...
     */
    public static String getFileSuffix(@NonNull File file) {
        String fileName = file.getName();
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 追加文本
     * @param file      要追加内容的文件
     * @param content   要追加的内容
     */
    public static void fileAppend(@NonNull File file, @NonNull String content) {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (fw != null) {
                    fw.flush();
                    if (pw != null) {
                        pw.close();
                    }
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取一个BufferedReader对象，方便读取文件数据中的内容
     * @param file 要获取BufferedReader对象的文件
     * @return
     */
    public static BufferedReader getReaderObj(@NonNull File file) {
        if (file.isDirectory()) {
            return null;
        }
        FileReader fr = null;
        try {
            fr = new FileReader(file.getPath());
            return new BufferedReader(fr);
        } catch (Exception e) {
            log.error("{}-->读取异常", file.getPath(), e);
        }finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取文件中所有的内容
     * @param file  要获取内容的数据
     * @return
     */
    public static String[] getFileContentAll(@NonNull File file) {
        BufferedReader br = getReaderObj(file);
        String line = "";
        StringBuilder builder = new StringBuilder();
        try {
            while (StringUtils.isNotBlank(line = br.readLine())) {
                builder.append(line).append("\n");
            }
            br.close();
            return builder.toString().split("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    /**
     * 批量处理文件
     * @param file      要处理的文件
     * @param dispose   怎么处理
     */
    public static void disposeFile(@NonNull File file, Dispose dispose) {
        if (file.isDirectory()) {
            //如果是文件夹
            File[] files = file.listFiles();
            for (File filed : files) {
                disposeFile(filed, dispose);
            }
        } else {
            //如果是文件
            dispose.action(file);
        }
    }

    /**
     * 处理文件抽象类
     */
    abstract static class Dispose{
        abstract void action(File file);
    }

    public static void main(String[] args) {
        File file = new File("D:\\Downloads\\aa");
        disposeFile(file, new Dispose() {
            @Override
            void action(File file) {
                System.err.println(file.getPath());
            }
        });
    }

}
