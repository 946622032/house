package cn.kgc.service.impl;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import cn.kgc.mapper.HouseMapper;
import cn.kgc.service.HouseService;
import cn.kgc.utils.Condition;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public Integer addHouse(House house) {
        return houseMapper.insertSelective(house);
    }

    @Override
    public PageInfo<House> getHouseData(Integer uid, PageUtil pageUtil) {
        //1.开启分页查询
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //2.查询房东发布信息
        List<House> houseList = houseMapper.getHouseData(uid);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public House findHouseById(String id) {
        return houseMapper.selectHouseById(id);
    }

    @Override
    public Integer updateHouseById(House house) {
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public House findHouseDetail(String id) {
        return houseMapper.selectHouseDetail(id);
    }

    @Override
    public Integer delHouseById(String id, Integer delState) {
        House house = new House();
        house.setId(id);
        house.setIsdel(delState);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> findAllHouse(PageUtil pageUtil) {
        //1.开启分页查询
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.selectAllHouse();
        PageInfo pageInfo = new PageInfo(houseList);
        return pageInfo;
    }

    @Override
    public Integer passHouse(String houseId, Integer passState) {
        House house = new House();
        house.setId(houseId);
        house.setIspass(passState);
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> getDelHouseData(Integer uid, PageUtil pageUtil) {
        //1.开启分页查询
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //2.查询房东发布信息
        List<House> houseList = houseMapper.getDelHouseData(uid);
        PageInfo<House> pageInfo = new PageInfo<>(houseList);
        return pageInfo;
    }

    @Override
    public PageInfo<House> findAllCheckHouse(PageUtil pageUtil, Integer passState) {
        //1.开启分页查询
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.selectCheckHouse(passState);
        return new PageInfo<>(houseList);
    }

    @Override
    public PageInfo<House> getBroswerHouse(Condition condition) {
        //1.开启分页查询
        PageHelper.startPage(condition.getPage(),condition.getRows());
        List<House> houseList = houseMapper.getBroswerHouse(condition);
        PageInfo pageInfo = new PageInfo(houseList);
        return pageInfo;
    }


}
