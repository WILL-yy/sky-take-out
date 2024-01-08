package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Api("通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 实现文件上传
     * @param file
     * @return
     */
    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file)  {
        log.info("开始文件上传：{}",file);
        String originalFilename = file.getOriginalFilename();

        //截取原始文件名后缀
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        //构造新文件名
        String objectName = UUID.randomUUID().toString() + extension;

        try {
            //上传文件，并返回文件访问路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        }catch (IOException e){
            log.error("文件上传失败: {}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
