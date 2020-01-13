package cn.kgc.pcontroller;

import cn.kgc.domain.Users;
import cn.kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller(value = "userController2")
@RequestMapping("/page/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("checkUsername")
    @ResponseBody
    public String checkUsername(String name){
        Boolean res = userService.checkUsername(name);
        return "{\"result\":"+res+"}";
    }

    @RequestMapping("regUsers")
    public String regUsers(Users users){
        Integer res = userService.addUser(users);
        if (res>0){
            return "redirect:login.jsp";
        }else{
            return "redirect:regs.jsp";
        }
    }

    @RequestMapping("loginUsers")
    public String loginUsers(String username, String password, HttpSession session){
        Users users = userService.loginUser(username, password);
        if (users==null){
            return "redirect:login.jsp";
        }else{
            //使用session保存用户信息
            session.setAttribute("users",users);
            //设置session存活时间
            session.setMaxInactiveInterval(10*60);
            return "redirect:getHouseData";

        }
    }

    @RequestMapping("selectUsers")
    @ResponseBody
    public String selectUsers(Integer uid,String password){
        Users users = userService.selectUser(uid,password);
        if (users==null){
            return "{\"result\":-1}";
        }else {
            return "{\"result\":1}";
        }
    }

    @RequestMapping("updateUsers")
    public String updateUsers(String password,Integer id){
        Boolean b = userService.updateUser(password,id);
        if (b){
            return "redirect:login.jsp";
        }else{
            return "redirect:selectUser.jsp";
        }
    }

}
