package com.wyy.service.user;

import com.wyy.dao.BaseDao;
import com.wyy.dao.user.UserDao;
import com.wyy.dao.user.UserDaoImpl;
import com.wyy.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    //业务层都会调用dao层，所以我们要引入Dao层
    private UserDao userDao;

    //    实例化dao层对象
    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    /**
     * 在这个业务层调用Dao层的具体数据库操作，获得要登录的用户对象
     *
     * @param userCode     用户编码
     * @param userPassword 用户密码
     * @return 要登录的用户对象，为null则没有该用户
     */
    public User login(String userCode, String userPassword) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            //通过业务层调用对应的具体数据库操作，这里就是获取要登录的用户对象
            user = userDao.getLoginUser(connection, userCode, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return user;
    }

    /**
     * 根据用户id修改用户密码
     *
     * @param id       用户id
     * @param password 用户密码
     * @return 返回成功或失败
     */
    public boolean update(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

}
