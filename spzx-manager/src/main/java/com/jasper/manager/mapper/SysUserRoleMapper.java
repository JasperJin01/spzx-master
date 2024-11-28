package com.jasper.manager.mapper;

import java.util.List;

public interface SysUserRoleMapper {
    List<Integer> selectRoleIdsByUserId(Integer userId);

    void deleteByUserId(Long userId);

    void insertBatch(Long userId, List<Long> roleIds);

    List<Integer> selectUserIdByRoleId(Integer id);
}
