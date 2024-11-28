package com.jasper.manager.controller;

import com.jasper.manager.service.FileUploadService;
import com.jasper.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
@CrossOrigin(allowCredentials = "true", originPatterns = "*", allowedHeaders = "*")
public class FailUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/fileUpload")
    public Result<String> fileUpload(@RequestParam(value = "file")MultipartFile multipartFile) {
        System.out.println("上传文件！");
        String fileurl = fileUploadService.fileUpload(multipartFile);
        return Result.ok(fileurl);
    }

}
