package com.sharding.jdbc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sharding.jdbc.mapper.PackMapper;
import com.sharding.jdbc.po.Pack;
import com.sharding.jdbc.service.PackService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qizenan
 * @date 2020/10/08 13:12
 */
@Service
public class PackServiceImpl implements PackService {
    @Resource
    private PackMapper packMapper;

    @Override
    public Pack createPack(Pack pack) {
        packMapper.createPack(pack);
        return pack;
    }

    @Override
    public boolean updatePack(Pack pack) {
        int result = packMapper.updatePack(pack);
        return result > 0;
    }

    @Override
    public boolean deletePack(long packId) {
        int result = packMapper.deletePack(packId);
        return result > 0;
    }

    @Override
    public Pack getPackById(long packId) {
        return packMapper.getPackById(packId);
    }

    @Override
    public PageInfo<Pack> getPackList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Pack> packList = packMapper.getPackList();
        return new PageInfo<>(packList);
    }
}
