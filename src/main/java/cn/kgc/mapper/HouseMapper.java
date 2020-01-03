package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import cn.kgc.utils.Condition;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    /**
     * 根据id查询房东发布房子的信息
     * @param uid   房东的id值
     * @return  发布房子的list集合
     */
    List<House> getHouseData(Integer uid);

    /**
     * 根据房屋id获取房子信息
     * @param id    房屋的id值
     * @return      房屋的信息
     */
    House selectHouseById(String id);

    House selectHouseDetail(String id);

    /**
     * 查询所有的出租房
     * @return
     */
    List<House> selectAllHouse();

    /**
     * 根据id查询房东已经删除的信息
     * @param uid   房东的id值
     * @return      返回删除房子的值
     */
    List<House> getDelHouseData(Integer uid);

    List<House> selectCheckHouse(Integer passState);

    /**
     * 查看浏览的出租房
     * @param condition 搜索条件
     * @return  出租房集合
     */
    List<House> getBroswerHouse(Condition condition);
}