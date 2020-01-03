package cn.kgc.pcontroller;

import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.service.HouseService;
import cn.kgc.utils.Condition;
import cn.kgc.utils.FileUploadUtil;
import cn.kgc.utils.PageUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

@Controller
@RequestMapping("/page/")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @RequestMapping("addHouse")
    public String addHouse(HttpSession session,House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
            //1.实现文件上传
            String saveFileName=FileUploadUtil.upload(pfile);
            //2.将数据插入数据库
            house.setId(System.currentTimeMillis()+"");
            Users users = (Users)session.getAttribute("users");
            house.setUserId(users.getId());
            //设置图片路径
            house.setPath(saveFileName);
            //添加业务实现添加
            houseService.addHouse(house);
            return "fabu";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("getHouseData")
    public String getHouseData(HttpSession session, PageUtil pageUtil, Model model){
        Users users = (Users)session.getAttribute("users");
        pageUtil.setRows(5);
        if (pageUtil.getPage()==null){
            pageUtil.setPage(1);
        }
        PageInfo<House> pageInfo = houseService.getHouseData(users.getId(), pageUtil);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }

    @RequestMapping("editHouse")
    public String editHouse(String id,Model model){
        House house = houseService.findHouseById(id);
        model.addAttribute("house",house);
        return "updatefabu";
    }

    @RequestMapping("upHouse")
    public String upHouse(House house,String oldPicPath, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
           //1.判断图片有没有选中
            if (!pfile.getOriginalFilename().equals("")) {
                String saveFileName = FileUploadUtil.upload(pfile);
                //设置修改图片的路径
                house.setPath(saveFileName);

                //删除旧图
                FileUploadUtil.deleteFile(oldPicPath);
            }

            //2.修改数据库信息
            System.out.println("id的值为"+house.getId());
            houseService.updateHouseById(house);
            return "redirect:getHouseData";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("showHouse")
    public String showHouse(String id,Model model){
        House house = houseService.findHouseDetail(id);
        model.addAttribute("house",house);
        return "details";
    }

    @RequestMapping("delHouse")
    public String delHouse(String id){
        //1.表示已经删除
        Integer temp = houseService.delHouseById(id, 1);
        return "redirect:getHouseData";
    }

    @RequestMapping("getDelHouseData")
    public String getDelHouseData(HttpSession session, PageUtil pageUtil, Model model){
        Users users = (Users)session.getAttribute("users");
        pageUtil.setRows(5);
        if (pageUtil.getPage()==null){
            pageUtil.setPage(1);
        }
        PageInfo<House> pageInfo = houseService.getDelHouseData(users.getId(), pageUtil);
        model.addAttribute("pageInfo",pageInfo);
        return "delHouse";
    }

    @RequestMapping("recoveryHouse")
    public String recoveryHouse(String id){
        //1.表示恢复已经删除的房子
        Integer temp = houseService.delHouseById(id, 0);
        return "redirect:getHouseData";
    }

    @RequestMapping("getBroswerHouse")
    public String getBroswerHouse(Condition condition,Model model){
        condition.setRows(5);
        PageInfo<House> pageInfo = houseService.getBroswerHouse(condition);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("condition",condition);
        return "list";
    }

}
