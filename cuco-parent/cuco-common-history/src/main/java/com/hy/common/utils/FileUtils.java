package com.hy.common.utils;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import javax.imageio.stream.ImageInputStream;
import java.io.*;

/**
 * Created by WangShuai on 2016/8/17.
 */
public class FileUtils {

    /**
     * 获取文件名后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        if(StringUtils.isEmpty(fileName) || !fileName.contains(".")){
            return "";
        }
        String suffix = fileName.substring( fileName.lastIndexOf(".")+1 ,fileName.length() );
        return suffix;
    }

    /**
     * 关闭输入输出流
     * @param in
     * @param os
     */
    public static void closeInOsStream(FileInputStream in, FileOutputStream os){
        closeInputStream(in);
        closeOutputStream(os);
    }

    public static void closeInputStream(InputStream in){
        if(in!=null){
            try {
                in.close();
            } catch (IOException e) {
                LogUtil.getLogger().error("关闭输入流出错：",e);
            }
        }
    }

    public static void closeOutputStream(OutputStream os){
        if(os!=null){
            try {
                os.close();
            } catch (IOException e) {
                LogUtil.getLogger().error("关闭输出流出错：",e);
            }

            try {
                os.flush();
            }catch (IOException e){
                LogUtil.getLogger().error("关闭输出流出错：",e);
            }
        }
    }

    public static void closeImageInputStream(ImageInputStream iis){
        if(iis != null){
            try {
                iis.flush();
            } catch (IOException e) {
                LogUtil.getLogger().error("关闭ImageInputStream出错：",e);
            }
            try {
                iis.close();
            } catch (IOException e) {
                LogUtil.getLogger().error("关闭ImageInputStream出错：",e);
            }
        }
    }

    public static Long getFileSize(String filePath){
        try {
            File file = new File(filePath);
            if(file ==null){
                return null;
            }
            if(!file.exists()){
                return null;
            }
            return file.length();
        }catch (Exception e){
            LogUtil.getLogger().error("获取文件大小失败",e);
            return null;
        }
    }

    public static final void mkdirs(final String... path) {
        for (String foo : path) {
            final String realPath = FilenameUtils.normalizeNoEndSeparator(foo, true);
            final File folder = new File(realPath);
            if (!folder.exists() || folder.isFile()) {
                folder.mkdirs();
            }
        }
    }

    @Test
    public void test(){
        System.out.println( getSuffix("1.jpg") );
    }
}