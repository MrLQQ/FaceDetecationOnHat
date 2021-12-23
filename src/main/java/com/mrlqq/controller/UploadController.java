package com.mrlqq.controller;

import com.mrlqq.entity.DataJson;
import com.mrlqq.utils.UploadUtils;
import com.mrlqq.utils.pythonFace;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * 作者: DL代先生
 * 日期: 2021/4/29
 * 时间: 11:15
 * 描述: 上传的controller！
 * 内容:
 */
@Controller
@RequestMapping("upload")
public class UploadController {

    private static  final String SERVER_PATH="http://localhost:9999/";

    @RequestMapping("image")
    @ResponseBody
    public DataJson image(MultipartFile file){

        // 实例化python脚本
        pythonFace pythonFace = new pythonFace();

        //调用工具类完成文件上传
        String imagePath = UploadUtils.upload(file);
        System.out.println("imagePath = " + imagePath);
        // 截取文件名字
        String[] split = imagePath.split("/");
        String imgName = split[split.length-1];

        // 向python脚本传递图片名称
        String imgOnHatName = pythonFace.OnHat(imgName);
        imgOnHatName=SERVER_PATH+imgOnHatName;
        DataJson dataJson = new DataJson();
        if (imagePath != null){
            //创建一个HashMap用来存放图片路径
            HashMap hashMap = new HashMap();
            hashMap.put("src",imagePath);
            hashMap.put("onHat",imgOnHatName);
            dataJson.setCode(0);
            dataJson.setMsg("上传成功");
            dataJson.setData(hashMap);
        }else{
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }

    @RequestMapping("deleteImage")
    @ResponseBody
    public String deleteImage(){
        UploadUtils.delete("84e15dddb4284fc8a4877c93bcc9d81f-dl.jpg");
        return "1";
    }
}
