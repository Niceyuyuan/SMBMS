package com.wyy.service.user;
import com.wyy.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode, String password);
//    根据用户id修改用户密码
    public boolean update(int id,String password);
}
