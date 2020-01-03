package cn.kgc.controller;

import cn.kgc.domain.Users;
import cn.kgc.service.UserService;
import cn.kgc.utils.PageUtil;
import cn.kgc.utils.UserCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController       //相当于@Controller+@ResponseBody
@RequestMapping("/admin/")
public class UserController {
    @Autowired
    private UserService UserService;

    @RequestMapping("getUsers")
    public Map<String,Object> getUsers(UserCondition userCondition){
        PageInfo<Users> pageInfo = UserService.getUsersByPage(userCondition);
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
