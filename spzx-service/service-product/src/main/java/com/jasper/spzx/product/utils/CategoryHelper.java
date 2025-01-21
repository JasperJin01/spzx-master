package com.jasper.spzx.product.utils;

import com.jasper.model.entity.product.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryHelper {

    public static List<Category> buildTree(List<Category> categories) {
        List<Category> treeList = new ArrayList<>();
        for (Category category: categories) {
            if (category.getParentId() == 0) { // 父节点
                treeList.add(findChildren(categories, category));
            }
        }
        return treeList;
    }

    /**
     *
     * @param categoryList
     * @param head
     * @return
     */
    private static Category findChildren(List<Category> categoryList, Category head) {
        for (Category category: categoryList) {
            if (category.getParentId().equals(head.getId())) {
                head.getChildren().add(findChildren(categoryList, category));
            }
        }
        if (head.getChildren() == null || head.getChildren().size() == 0)
            head.setHasChildren(false);
        return head;
    }


}
