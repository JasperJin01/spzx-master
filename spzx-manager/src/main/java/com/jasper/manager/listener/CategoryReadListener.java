package com.jasper.manager.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jasper.manager.mapper.CategoryMapper;
import com.jasper.model.vo.product.CategoryExcelVo;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// EasyExcel 的工作机制是基于事件驱动（Event-Driven）
// 这里定义了增强方法（读取到数据需要做什么事情、收尾时需要做什么事情）
// 当读取到一行数据时，会调用 invoke 方法，当所有数据读取完毕时，会调用 doAfterAllAnalysed 方法
@NoArgsConstructor
public class CategoryReadListener extends AnalysisEventListener<CategoryExcelVo> {

    List<CategoryExcelVo> list = new ArrayList<>();

    CategoryMapper categoryMapper;

    public CategoryReadListener(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void invoke(CategoryExcelVo data, AnalysisContext context) {
        list.add(data);
        if (list.size() >= 100) {
            categoryMapper.insertBath(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if (!list.isEmpty())
            categoryMapper.insertBath(list);
    }
}
