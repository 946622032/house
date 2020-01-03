package cn.kgc.controller;

import cn.kgc.domain.House;
import cn.kgc.service.HouseService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller(value = "houseController2")
@RequestMapping("/admin/")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("getHouse")
    @ResponseBody
    public Map<String,Object> getHouse(PageUtil pageUtil){
        PageInfo<House> pageInfo = houseService.findAllHouse(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("updatePassState")
    @ResponseBody
    public Map<String,Object> updatePassState(String id,Integer state){
        Integer temp = houseService.passHouse(id, state);
        Map<String,Object> map = new HashMap<>();
        map.put("result",temp);
        return map;
    }

    @RequestMapping("findAllCheckHouse")
    @ResponseBody
    public Map<String,Object> findAllCheckHouse(PageUtil pageUtil){
        PageInfo<House> pageInfo = houseService.findAllCheckHouse(pageUtil, 1);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("findAllNotCheckHouse")
    @ResponseBody
    public Map<String,Object> findAllNotCheckHouse(PageUtil pageUtil){
        PageInfo<House> pageInfo = houseService.findAllCheckHouse(pageUtil, 0);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
