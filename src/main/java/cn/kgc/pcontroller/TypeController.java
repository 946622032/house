package cn.kgc.pcontroller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller(value = "typeController2")
@RequestMapping("/page/")
public class TypeController {
    @Autowired
    private TypeService TypeService;

    @RequestMapping("getTypeAll")
    @ResponseBody
    public List<Type> getTypeAll(){
        //查询所有类型
        List<Type> typeList = TypeService.getTypeAll();
        return typeList;
    }
}
