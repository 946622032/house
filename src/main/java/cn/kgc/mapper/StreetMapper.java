package cn.kgc.mapper;

import cn.kgc.domain.Street;
import cn.kgc.domain.StreetExample;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface StreetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Street record);

    int insertSelective(Street record);

    List<Street> selectByExample(StreetExample example);

    Street selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Street record);

    int updateByPrimaryKey(Street record);

    @Delete(" DELETE FROM street WHERE district_id=#{did} ")
    Integer deleteByDistrictId(Integer did);

    int deleteMoreStreet(Integer[] ids);
}