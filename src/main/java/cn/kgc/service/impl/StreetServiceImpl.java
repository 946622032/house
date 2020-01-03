package cn.kgc.service.impl;

import cn.kgc.domain.Street;
import cn.kgc.domain.StreetExample;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.StreetService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StreetServiceImpl implements StreetService {
    @Autowired
    private StreetMapper StreetMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Street> getStreetByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        StreetExample StreetExample = new StreetExample();
        List<Street> StreetList = StreetMapper.selectByExample(StreetExample);
        PageInfo<Street> pageInfo = new PageInfo<>(StreetList);
        return pageInfo;
    }

    @Override
    public Integer addStreet(Street Street) {
        return StreetMapper.insertSelective(Street);
    }

    @Override
    public Street getStreet(Integer id) {
        return StreetMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateStreet(Street Street) {
        return StreetMapper.updateByPrimaryKeySelective(Street);
    }

    @Override

    public void deleteStreet(Integer id) {
        //1.先删除街道
        streetMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreStreet(Integer[] ids) {

        return StreetMapper.deleteMoreStreet(ids);
    }

    @Override
    public List<Street> getStreetByDid(Integer did) {
        //创建查询条件
        StreetExample streetExample = new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        List<Street> streetList = StreetMapper.selectByExample(streetExample);
        return streetList;
    }
}
