package cn.kgc.service;

import cn.kgc.domain.House;
import cn.kgc.utils.Condition;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HouseService {

    Integer addHouse(House house);


    PageInfo<House> getHouseData(Integer uid, PageUtil pageUtil);

    House findHouseById(String id);

    Integer updateHouseById(House house);

    House findHouseDetail(String id);

    /**
     * 根据id更新isDel的字段状态
     * @param id    房子的编号
     * @param delState  字段的状态
     * @return  影响的行数
     */
    Integer delHouseById(String id,Integer delState);

    /**
     * 查询所有的出租房
     * @return
     */
    PageInfo<House> findAllHouse(PageUtil pageUtil);

    /**
     * 根据id更新出租房的审核状态
     * @param houseId   房子的编号
     * @param passState 状态
     * @return  影响的行数
     */
    Integer passHouse(String houseId,Integer passState);

    /**
     * 查询已删除的房子
     * @param uid
     * @param pageUtil
     * @return
     */
    PageInfo<House> getDelHouseData(Integer uid, PageUtil pageUtil);

    /**
     * 查询审核的房屋信息
     * @param pageUtil
     * @param passState
     * @return
     */
    PageInfo<House> findAllCheckHouse(PageUtil pageUtil,Integer passState);

    /**
     * 浏览所有的出租房
     * @return
     */
    PageInfo<House> getBroswerHouse(Condition condition);
}
