package cn.kgc.service;

import cn.kgc.domain.Type;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TypeService {

    /**
     *查询区域带分页
     * @param  pageUtil
     * page接收页码，rows接收页大小
     * @return PageInfo<Type>
     */
    PageInfo<Type> getTypeByPage(PageUtil pageUtil);

    /**
     *
     * @param type  区域实体信息
     * @return  影响的行数
     */
    Integer addType(Type type);

    /**
     *
     * @param id 根据id查询数据
     * @return 区域的实体
     */
    Type getType(Integer id);

    /**
     *
     * @param type 区域实体信息
     * @return  影响的行数
     */
    Integer updateType(Type type);

    void deleteType(Integer id);

    /**
     *
     * @param ids 传递的是id的数组
     * @return 返回影响的行数
     */
    int deleteMoreType(Integer[] ids);

    /**
     * 获取所有类型的信息
     * @return
     */
    List<Type> getTypeAll();
}
