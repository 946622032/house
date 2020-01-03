package cn.kgc.pcontroller;

import cn.kgc.domain.District;
import cn.kgc.domain.Type;
import cn.kgc.service.DistrictService;
import cn.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "districtController2")
@RequestMapping("/page/")
public class DistrictController {
    @Autowired
    private DistrictService districtService;

    @RequestMapping("getDistrictAll")
    @ResponseBody
    public List<District> getDistrictAll(){
        //查询所有类型
        List<District> districtList = districtService.getDistrictAll();
        return districtList;
    }
}
