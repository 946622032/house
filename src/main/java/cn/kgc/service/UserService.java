package cn.kgc.service;

import cn.kgc.domain.Users;
import cn.kgc.utils.PageUtil;
import cn.kgc.utils.UserCondition;
import com.github.pagehelper.PageInfo;

public interface UserService {

    /**
     *查询区域带分页
     * @param  userCondition
     *name:接收名字条件，tel接收手机号码条件
     * page接收页码，rows接收页大小
     * @return PageInfo<Users>
     */
    PageInfo<Users> getUsersByPage(UserCondition userCondition);

    /**
     * 检查用户名是否存在
     * @param name  接收前台传入的名字
     * @return      true:用户名可用，false：用户名不可用
     */
    Boolean checkUsername(String name);

    /**
     * 用户注册功能
     * @param users 接收前台传送的条件
     * @return  影响的行数
     */
    Integer addUser(Users users);

    /**
     * 用户登录功能
     * @param username  前台输入的用户名
     * @param password  前台输入的密码
     * @return  返回的实体
     */
    Users loginUser(String username,String password);
    
}
