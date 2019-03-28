package com.njtech.smartuniversity.controller;

import com.njtech.smartuniversity.util.JsonResult;
import com.njtech.smartuniversity.util.JsonUtil;
import com.njtech.smartuniversity.util.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by ritchie on 7/5/18
 */
@RestController
@RequestMapping("/api/images")
@Api(value = "/api/images", tags = {"图片"}, description = "图片相关")
public class ImgController {

    @Autowired
    QiniuUtil mQiniuUtil;


    @PutMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MERCHANT')")
    @ApiOperation(value = "图片上传接口", notes = "图片上传")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "图片", name = "image", dataType = "file", paramType = "form", required = true)
    })
    public JsonResult uploadImgQiniu(@RequestParam("image") MultipartFile multipartFile) throws IOException {
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        long time = System.currentTimeMillis();
        String path = mQiniuUtil.uploadImg(inputStream, time + "");
        return new JsonUtil().successDataWithMessage(path, "图片上传成功");
    }
}
