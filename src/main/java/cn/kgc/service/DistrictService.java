package cn.kgc.service;

import cn.kgc.domain.District;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DistrictService {

    /**
     *查询区域带分页
     * @param  pageUtil
     * page接收页码，rows接收页大小
     * @return PageInfo<District>
     */
    PageInfo<District> getDistrictByPage(PageUtil pageUtil);

    /**
     *
     * @param district  区域实体信息
     * @return  影响的行数
     */
    Integer addDistrict(District district);

    /**
     *
     * @param id 根据id查询数据
     * @return 区域的实体
     */
    District getDistrict(Integer id);

    /**
     *
     * @param district 区域实体信息
     * @return  影响的行数
     */
    Integer updateDistrict(District district);

    void deleteDistrict(Integer id);

    /**
     *
     * @param ids 传递的是id的数组
     * @return 返回影响的行数
     */
    int deleteMoreDistrict(Integer[] ids);

    /**
     * 查找所有的区域信息
     * @return
     */
    List<District> getDistrictAll();
}
