package com.jasper.manager.service.impl;

import cn.hutool.core.date.DateTime;
import com.jasper.manager.service.FileUploadService;
import io.minio.MinioClient;
import io.minio.MinioProperties;
import io.minio.PutObjectArgs;
import lombok.SneakyThrows;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @SneakyThrows // NOTE 简化异常处理
    @Override
    public String fileUpload(MultipartFile multipartFile) {

        // FIXME 这里需要try-cache吗？不是有统一异常处理吗？

        // 客户端
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://120.26.83.211:9001")
                .credentials("admin", "admin123456")
                .build();

        // 参数
        String timeStr = new DateTime().toString("/yyyy/MM/dd/");
        String fileName = "a" + new DateTime().toString("HHmmss") + multipartFile.getOriginalFilename();
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket("spzx")
                .object("avatar" + timeStr + fileName)
                .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                .build();

        // 上传
        minioClient.putObject(putObjectArgs);

        // url
        String fileurl = "http://120.26.83.211:9001/spzx/avatar" + timeStr + fileName;

        return fileurl;
    }
}
