package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.DistrictService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<District> getDistrictByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        DistrictExample districtExample = new DistrictExample();
        List<District> districtList = districtMapper.selectByExample(districtExample);
        PageInfo<District> pageInfo = new PageInfo<>(districtList);
        return pageInfo;
    }

    @Override
    public Integer addDistrict(District district) {
        return districtMapper.insertSelective(district);
    }

    @Override
    public District getDistrict(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    @Override

    public void deleteDistrict(Integer id) {
        //1.先删除街道
        streetMapper.deleteByDistrictId(id);
        //int i=1/0;
        //2.先删除区域
        districtMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreDistrict(Integer[] ids) {

        return districtMapper.deleteMoreDistrict(ids);
    }

    @Override
    public List<District> getDistrictAll() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
