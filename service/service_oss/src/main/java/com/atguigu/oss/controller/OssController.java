package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author XieShifeng
 * @create 2021-10-12 10:30
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
//        获取上传问价 MultipartFile
//        返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
