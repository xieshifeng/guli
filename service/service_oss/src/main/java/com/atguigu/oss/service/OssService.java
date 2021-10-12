package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author XieShifeng
 * @create 2021-10-12 10:31
 */
public interface OssService {
    //上传头像到oss
    String uploadFileAvatar(MultipartFile file);
}
