package cn.kgc.service;

import cn.kgc.domain.Street;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface StreetService {

    /**
     *查询区域带分页
     * @param  pageUtil
     * page接收页码，rows接收页大小
     * @return PageInfo<Street>
     */
    PageInfo<Street> getStreetByPage(PageUtil pageUtil);

    /**
     *
     * @param street  区域实体信息
     * @return  影响的行数
     */
    Integer addStreet(Street street);

    /**
     *
     * @param id 根据id查询数据
     * @return 区域的实体
     */
    Street getStreet(Integer id);

    /**
     *
     * @param street 区域实体信息
     * @return  影响的行数
     */
    Integer updateStreet(Street street);

    void deleteStreet(Integer id);

    /**
     *
     * @param ids 传递的是id的数组
     * @return 返回影响的行数
     */
    int deleteMoreStreet(Integer[] ids);

    /**
     *
     * @param did   区域的id值
     * @return  返回街道信息
     */
    List<Street> getStreetByDid(Integer did);
}
