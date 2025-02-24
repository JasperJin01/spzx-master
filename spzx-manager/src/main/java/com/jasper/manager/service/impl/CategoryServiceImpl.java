package com.jasper.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.jasper.manager.listener.CategoryReadListener;
import com.jasper.manager.mapper.CategoryMapper;
import com.jasper.manager.service.CategoryService;
import com.jasper.model.entity.product.Category;
import com.jasper.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findByParentId(Long id) {
        List <Category> categories = categoryMapper.selectByParentId(id);
        for (Category category: categories) {
            Long count = categoryMapper.selectCountByParentId(category.getId());
            if (count <= 0) category.setHasChildren(false);
        }
        return categories;
    }

    @SneakyThrows
    @Override
    public void importData(MultipartFile multipartFile) {
        InputStream inputStream = multipartFile.getInputStream();
        EasyExcel.read(inputStream, CategoryExcelVo.class,
                        new CategoryReadListener(categoryMapper))
                .sheet("分类数据")
                .doRead();
    }

    /**
     * 导出分类为表格
     * @param response（直接将导出的表格写入到response中）
     */
    @SneakyThrows
    @Override
    public void exportData(HttpServletResponse response) {
        List<Category> categories = categoryMapper.selectAll();
        List<CategoryExcelVo> categoryExcelVos = new ArrayList<>();
        for (Category category: categories) {
            CategoryExcelVo categoryExcelVo = new CategoryExcelVo();
            // note spring提供的工具，通过反射将源对象的属性值赋值到目标对象中
            BeanUtils.copyProperties(category, categoryExcelVo, CategoryExcelVo.class);
            categoryExcelVos.add(categoryExcelVo);
        }
        // 将导出的表格写入到response中
        EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class)
                .sheet("分类数据")
                .doWrite(categoryExcelVos);
    }


}
