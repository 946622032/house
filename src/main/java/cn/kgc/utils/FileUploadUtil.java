package cn.kgc.utils;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

public class FileUploadUtil {
    private static final String savePosition="f:\\images\\";

    /**
     * 上传文件的工具类
     * @param pfile 上传的文件
     * @return      上传文件的名称
     */
    public static String upload(CommonsMultipartFile pfile){
        try {
            String fname = pfile.getOriginalFilename();
            String fexpName = fname.substring(fname.lastIndexOf("."));

            String unique=System.currentTimeMillis()+"";
            String saveFileName=unique+fexpName;
            String savePath=savePosition+saveFileName;
            File savefile=new File(savePath);
            pfile.transferTo(savefile); //上传
            return saveFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFile(String fileName){
        File file=new File(savePosition+fileName);
        return file.delete();
    }
}
