package com.mrlqq.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * 作者: DL代先生
 * 日期: 2021/4/29
 * 时间: 11:10
 * 描述: 文件上传的工具类！
 * 内容: 这里封装了文件上传的核心方法
 */
public class UploadUtils {
    //定义一个上传文件的路径
//    private static final String BASE_PATH = "E:/imageUpload/";
    private static final String BASE_PATH = "/home/FaceDetectionOnHat/imageUpload/";
//    private static final String BASE_PATH = "src/main/resources/static/image/";
//    private static final String  BASE_PATH = "src/main/resources/static/";
    //定义文件服务器的访问地址
    private static  final String SERVER_PATH="http://8.136.9.12:9999/";
    public static String upload(MultipartFile file){
        //获得上传文件的名称
        String filename = file.getOriginalFilename();
        //创建UUID，用来保持文件名字的唯一性
        String uuid = UUID.randomUUID().toString().replace("-","");
        //进行文件名称的拼接
        String newFileName = uuid+"-"+filename;
        // 图片存储目录及图片名称
        String url_path = newFileName;
        System.out.println("BASE_PATH = " + BASE_PATH);
        //创建文件实例对象
        File uploadFile = new File(BASE_PATH,newFileName);
        //判断当前文件是否存在
        if (!uploadFile.exists()){
            //如果不存在就创建一个文件夹
            uploadFile.mkdirs();
        }
        //执行文件上传的命令
        try {
            file.transferTo(uploadFile);
        } catch (IOException e) {
            return null;
        }
        //将上传的文件名称返回，注意，这里我们返回一个 服务器地址
        return SERVER_PATH+url_path;
    }

    public static void delete(String filename){
        File file = new File(BASE_PATH, filename);
        if (file.exists()){
            file.delete();
        }
    }
}
