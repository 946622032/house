package cn.kgc.service.impl;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.UserService;
import cn.kgc.utils.MD5Utils;
import cn.kgc.utils.UserCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public PageInfo<Users> getUsersByPage(UserCondition userCondition) {
        //1.开启分页查询
        PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
        //2.创建查询条件
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //动态查询：判断条件是否为空
        if (userCondition.getName()!=null){
            criteria.andNameLike("%"+userCondition.getName()+"%");
        }
        if (userCondition.getTel()!=null){
            criteria.andTelephoneLike("%"+userCondition.getTel()+"%");
        }
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        PageInfo<Users> pageInfo = new PageInfo<>(usersList);
        return pageInfo;
    }

    @Override
    public Boolean checkUsername(String name) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        //添加条件
        criteria.andIsadminEqualTo(new Integer("0"));
        criteria.andNameEqualTo(name);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList.size()!=0){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public Integer addUser(Users users) {
        //1.使用MD5进行加密
        String newPassword = MD5Utils.md5Encrypt(users.getPassword());
        users.setPassword(newPassword);
        //设置房东
        users.setIsadmin(0);
        int result = usersMapper.insertSelective(users);
        return result;
    }

    @Override
    public Users loginUser(String username, String password) {
        //1.使用MD5进行加密
        UsersExample usersExample = new UsersExample();
        //2.设置查询条件
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));
        criteria.andNameEqualTo(username);
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        if (usersList!=null&&usersList.size()>0){
             return usersList.get(0);
        }
        return null;
    }
}
