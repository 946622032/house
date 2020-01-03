package cn.kgc.controller;

import cn.kgc.domain.Street;
import cn.kgc.service.StreetService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController       //相当于@Controller+@ResponseBody
@RequestMapping("/admin/")
public class StreetController {
    @Autowired
    private StreetService StreetService;

    @RequestMapping("getStreet")
    public Map<String,Object> getStreet(PageUtil pageUtil){
        PageInfo<Street> pageInfo = StreetService.getStreetByPage(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addStreet")
    public Map<String,Object> addStreet(Street street){
        try {
            Integer result = StreetService.addStreet(street);
            Map<String,Object> map = new HashMap<>();
            map.put("result",result);
            return map;
        }catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("result",500);
            return map;
        }
    }

    @RequestMapping("updateStreet")
    public Street updateStreet(Integer id){
        try {
            Street Street = StreetService.getStreet(id);
            return Street;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateStreetById")       //一定要传id，name
    public String updateStreetById(Street street){
        try {
            Integer result = StreetService.updateStreet(street);
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("delStreet")       //一定要传id，name
    public String delStreet(Integer id){
        try {
            StreetService.deleteStreet(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    /*批量删除的控制器*/
    @RequestMapping("delMoreStreet")
    public String delMoreStreet(String ids){
        //将字符串转化为整数数组
        String[] arrays = ids.split(",");
        Integer[] id = new Integer[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            id[i]=Integer.parseInt(arrays[i]);
        }
        //调用业务层
        int temp = StreetService.deleteMoreStreet(id);
        return "{\"result\":"+temp+"}";

    }
}
