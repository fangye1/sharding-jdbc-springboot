package com.sharding.jdbc.mapper;

import com.sharding.jdbc.po.Pack;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/13 17:56
 */
@Mapper
public interface PackMapper {
    /**
     * 创建课程信息
     *
     * @param pack 实体
     * @return int
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert(" INSERT INTO packs ( title ,summary ,price ,created_at ,active) " +
            " VALUES ( #{title} ,#{summary} ,#{price} ,NOW() ,1) ")
    int createPack(Pack pack);

    /**
     * 更新课程信息
     *
     * @param pack 实体
     * @return int
     */
    @Update(" UPDATE packs SET title=#{title},summary=#{summary},price=#{price} WHERE id=#{id} ")
    int updatePack(Pack pack);

    /**
     * 删除课程信息
     *
     * @param id 唯一标识id
     * @return int
     */
    @Update("UPDATE packs SET active=0 WHERE id=#{id}")
    int deletePack(@Param("id") long id);

    /**
     * 按id查找课程信息
     *
     * @param id 唯一标识id
     * @return Pack
     */
    @Select("SELECT id,title,summary,price,created_at,active FROM packs WHERE id=#{id} LIMIT 1 ")
    Pack getPackById(@Param("id") long id);

    /**
     * 查找所有课程信息
     *
     * @return Pack 集合
     */
    @Select("SELECT id,title,summary,price,created_at,active FROM packs WHERE active=1 ORDER BY id DESC ")
    List<Pack> getPackList();
}
