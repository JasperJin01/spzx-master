package com.jasper.manager.service;

import com.jasper.model.entity.product.Category;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategoryService {

    List<Category> findByParentId(Long id);

    void importData(MultipartFile multipartFile);

    void exportData(HttpServletResponse response);
}
