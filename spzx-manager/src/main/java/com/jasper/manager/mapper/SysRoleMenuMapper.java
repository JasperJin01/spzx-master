package com.jasper.manager.mapper;

import java.util.List;
import java.util.Map;

public interface SysRoleMenuMapper {


    void deleteByRoleId(Long roleId);

    void insertBatch(Long roleId, List<Map<String, Number>> menuIdList);
}
