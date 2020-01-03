package cn.kgc.service.impl;

import cn.kgc.domain.Type;
import cn.kgc.domain.TypeExample;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.TypeService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper TypeMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Override
    public PageInfo<Type> getTypeByPage(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        TypeExample TypeExample = new TypeExample();
        List<Type> TypeList = TypeMapper.selectByExample(TypeExample);
        PageInfo<Type> pageInfo = new PageInfo<>(TypeList);
        return pageInfo;
    }

    @Override
    public Integer addType(Type Type) {
        return TypeMapper.insertSelective(Type);
    }

    @Override
    public Type getType(Integer id) {
        return TypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateType(Type Type) {
        return TypeMapper.updateByPrimaryKeySelective(Type);
    }

    @Override

    public void deleteType(Integer id) {
        //1.先删除区域
        TypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteMoreType(Integer[] ids) {

        return TypeMapper.deleteMoreType(ids);
    }

    @Override
    public List<Type> getTypeAll() {
        return TypeMapper.selectByExample(new TypeExample());
    }

}
