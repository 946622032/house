package cn.kgc.pcontroller;

import cn.kgc.domain.Street;
import cn.kgc.domain.Type;
import cn.kgc.service.StreetService;
import cn.kgc.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller(value = "streetController2")
@RequestMapping("/page/")
public class StreetController {
    @Autowired
    private StreetService streetService;

    @RequestMapping("getStreetByDid")
    @ResponseBody
    public List<Street> getStreetByDid(Integer did){
        //根据区域id查询所有街道信息
        List<Street> streetList = streetService.getStreetByDid(did);
        return streetList;
    }
}
