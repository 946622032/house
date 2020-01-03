package cn.kgc.mapper;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.domain.House;

import java.util.List;

public interface DistrictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(District record);

    int insertSelective(District record);

    List<District> selectByExample(DistrictExample example);

    District selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(District record);

    int updateByPrimaryKey(District record);

    int deleteMoreDistrict(Integer[] ids);


}