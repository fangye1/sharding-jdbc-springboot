package com.sharding.jdbc.service;

import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.po.Pack;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
public interface PackService {
    /**
     * 创建课程信息
     *
     * @param pack 实体
     * @return Pack
     */
    Pack createPack(Pack pack);

    /**
     * 更新课程信息
     *
     * @param pack 实体
     * @return true 成功，false 失败
     */
    boolean updatePack(Pack pack);

    /**
     * 删除课程信息
     *
     * @param packId 唯一标识id
     * @return true 成功，false 失败
     */
    boolean deletePack(long packId);

    /**
     * 按id查询课程信息
     *
     * @param packId 唯一标识id
     * @return Pack
     */
    Pack getPackById(long packId);

    /**
     * 分页查询所有课程信息
     *
     * @param pageNum  页码
     * @param pageSize 页数
     * @return Pack 集合
     */
    PageInfo<Pack> getPackList(int pageNum, int pageSize);
}
