package com.xy.xyv9background.controller;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.xy.v9.common.pojo.MultiUploadResultBean;
import com.xy.v9.common.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("file")
public class FileController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file) {
        System.out.println(file + "!!!!!!!");
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            StorePath storePath = fastFileStorageClient.uploadFile(inputStream, file.getSize(), extName, null);
            String fullPath = storePath.getFullPath();
            return ResultBean.success(new StringBuffer(image_server).append(fullPath).toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResultBean.error("你的网络当前不舒畅，请稍后再试!");
    }

    @RequestMapping("multiUpload")
    @ResponseBody
    public MultiUploadResultBean multiUpload(MultipartFile[] files) {
        MultiUploadResultBean resultBean = new MultiUploadResultBean();
        String[] data = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i] + "!!!!!!!");
            String originalFilename = files[i].getOriginalFilename();
            System.out.println(originalFilename);
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            InputStream inputStream = null;
            try {
                inputStream = files[i].getInputStream();
                StorePath storePath = fastFileStorageClient.uploadFile(inputStream, files[i].getSize(), extName, null);
                String fullPath = storePath.getFullPath();
                String path = new StringBuffer(image_server).append(fullPath).toString();
                data[i] = path;
            } catch (IOException e) {
                e.printStackTrace();
                resultBean.setErrno("1");
                return resultBean;
            } finally {
                try {
                    if (inputStream != null) inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        resultBean.setErrno("0");
        resultBean.setData(data);
        return resultBean;
    }
}
