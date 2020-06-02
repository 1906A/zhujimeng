package com.leyou.controller;

import org.apache.commons.lang.StringUtils;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhu
 * @date 2020/5/17 - 10:42
 */
@RestController
@RequestMapping("upload")
public class UploadController {


    public static final List<String> FILE_TYPE = Arrays.asList("jpg","png");


    @RequestMapping("image")
    public String uploadImage(@RequestParam("file") MultipartFile file){

        /**
         *
         *1.验证图片格式
         * 2、验证图片内容
         *  3，验证图片大小
         */
        try {

            String fileName = file.getOriginalFilename();//test.jpg

            String type = fileName.substring(fileName.lastIndexOf(".") + 1);

            String fileType = StringUtils.substringAfterLast(fileName, ".");

            System.out.println(type);
            System.out.println(fileType);

            //验证文件格式（后缀），通过截取字符串的方式获取

            if (!FILE_TYPE.contains(type)){
                return null;
            }

            //校验图片内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

            if (bufferedImage==null){
                return null;

            }

            String filePath = System.currentTimeMillis()+fileName;
            //file.transferTo(new File("F:/upload/"+filePath));

            //加载客户端配置文件，配置文件中指明了tracker服务器的地址
            ClientGlobal.init("fastdfs.conf");
            //验证配置文件是否加载成功
            System.out.println(ClientGlobal.configInfo());

            //创建TrackerClient对象，客户端对象
            TrackerClient trackerClient = new TrackerClient();

            //获取到调度对象，也就是与Tracker服务器取得联系
            TrackerServer trackerServer = trackerClient.getConnection();

            //创建存储客户端对象
            StorageClient storageClient = new StorageClient(trackerServer,null);

            //String[] upload_file = storageClient.upload_file("F:/test.jpg", "jpg", params);
            String[] upload_file = storageClient.upload_appender_file(file.getBytes(), fileType, null);


            for (String string : upload_file) {
                System.out.println(string);
                //group//HUYGKUTFYRDTH.jpg
            }


            return "http://image.leyou.com/"+upload_file[0]+"/"+upload_file[1];


        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

}
