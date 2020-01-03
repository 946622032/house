package cn.kgc.controller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController       //相当于@Controller+@ResponseBody
@RequestMapping("/admin/")
public class TypeController {
    @Autowired
    private TypeService TypeService;

    @RequestMapping("getType")
    public Map<String,Object> getType(PageUtil pageUtil){
        PageInfo<Type> pageInfo = TypeService.getTypeByPage(pageUtil);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    @RequestMapping("addType")
    public Map<String,Object> addType(Type type){
        try {
            Integer result = TypeService.addType(type);
            Map<String,Object> map = new HashMap<>();
            map.put("result",result);
            return map;
        }catch (Exception e){
            Map<String,Object> map = new HashMap<>();
            map.put("result",500);
            return map;
        }
    }

    @RequestMapping("updateType")
    public Type updateType(Integer id){
        try {
            Type Type = TypeService.getType(id);
            return Type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("updateTypeById")       //一定要传id，name
    public String updateTypeById(Type type){
        try {
            Integer result = TypeService.updateType(type);
            return "{\"result\":"+result+"}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    @RequestMapping("delType")       //一定要传id，name
    public String delType(Integer id){
        try {
            TypeService.deleteType(id);
            return "{\"result\":1}";
        }catch (Exception e){
            return "{\"result\":-1}";
        }
    }

    /*批量删除的控制器*/
    @RequestMapping("delMoreType")
    public String delMoreType(String ids){
        //将字符串转化为整数数组
        String[] arrays = ids.split(",");
        Integer[] id = new Integer[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            id[i]=Integer.parseInt(arrays[i]);
        }
        //调用业务层
        int temp = TypeService.deleteMoreType(id);
        return "{\"result\":"+temp+"}";

    }
}
