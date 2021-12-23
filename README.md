# 基于OpenCV人脸识别戴圣诞帽

## 介绍

本项目是通过OpenCV的人脸识别技术，通过识别到人脸的坐标，从而确定帽子的位置，然后将帽子贴到原始图片上。

原本是打算用Java+OpenCV实现的，可以Java中的OpenCV与Python中的使用方式不同。网上关于Java整合OpenCV的相关文章也不是很多。由于当时想要实现本项目的时候，距离圣诞节很近，留给我研究的时间不多。

最终选择使用之前编写过的[Python整合OpenCV戴帽子](https://github.com/MrLQQ/Face_detect)的基础上进行二次开发，在SpringBoot中调用Python脚本，间接完成。

## 技术

layui+SpringBoot，核心还是之前python的脚本，

## 使用

* 修改application.ymal、face.py、UploadUtils.java中的文件上传地址，修改为你的存储地址。
* 修改UploadUtils.java、UploadController.java中的`SERVER_PATH`服务地址
  * 本地就是`http://localhost:端口/`
  * 内网就是`http://内网ip:端口/`
  * 外网就是`http://外网ip:端口/`
* 调整pythonFace.java文件中的`String[] command = new String[]{"python", pythonFile, imgName};`调整`"pthobn"`与你的Python环境变量一直，以为可能有的人环境变量是`python2`、`python3`...

## 运行截图

![image-20211223181216942](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20211223181224.png)

![image-20211223182021775](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20211223182022.png)

## 注意

帽子是随机出现的，帽子存放在`src/main/java/com/mrlqq/Python/img`目录下。如需添加新的图片，需要将png图片保存到该目录下，在此之前应该调整帽子的中线在整个图片的中心

![image-20211223182426032](https://mrlqq-oss.oss-cn-beijing.aliyuncs.com/20211223182426.png)