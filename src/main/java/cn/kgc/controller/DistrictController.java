package cn.kgc.controller;

import cn.kgc.domain.District;
import cn.kgc.service.DistrictService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController       //相当于@Controller+@ResponseBody
@RequestMapping("/admin/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrict")
    public Map<String,Object> getDistrict(PageUtil pageUtil){
        PageInfo<District> pageInfo = districtService.getDistrictByPage(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addDistrict")
    public Map<String,Object> addDistrict(District district){
        try {
            Integer result = districtService.addDistrict(district);
            Map<String,Object> map = new HashMap<>();
            map.put("result",result);
            return map;
        }catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("result",500);
            return map;
        }
    }

    @RequestMapping("updateDistrict")
    public District updateDistrict(Integer id){
        try {
            District district = districtService.getDistrict(id);
            return district;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateDistrictById")       //一定要传id，name
    public String updateDistrictById(District district){
        try {
            Integer result = districtService.updateDistrict(district);
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("delDistrict")       //一定要传id，name
    public String delDistrict(Integer id){
        try {
            districtService.deleteDistrict(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    /*批量删除的控制器*/
    @RequestMapping("delMoreDistrict")
    public String delMoreDistrict(String ids){
        //将字符串转化为整数数组
        String[] arrays = ids.split(",");
        Integer[] id = new Integer[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            id[i]=Integer.parseInt(arrays[i]);
        }
        //调用业务层
        int temp = districtService.deleteMoreDistrict(id);
        return "{\"result\":"+temp+"}";

    }
}
