package com.wyy.dao.user;

import com.wyy.dao.BaseDao;
import com.wyy.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode, String userPassword) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from smbms_user where userCode=? and userPassword=?";
            Object[] params = {userCode, userPassword};

            try {
                rs = BaseDao.execute(connection, sql, params, rs, pstm);
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUserCode(rs.getString("userCode"));
                    user.setUserName(rs.getString("userName"));
                    user.setUserPassword(rs.getString("userPassword"));
                    user.setGender(rs.getInt("gender"));
                    user.setBirthday(rs.getDate("birthday"));
                    user.setPhone(rs.getString("phone"));
                    user.setAddress(rs.getString("address"));
                    user.setUserRole(rs.getInt("userRole"));
                    user.setCreatedBy(rs.getInt("createdBy"));
                    user.setCreationDate(rs.getTimestamp("creationDate"));
                    user.setModifyBy(rs.getInt("modifyBy"));
                    user.setModifyDate(rs.getTimestamp("modifyDate"));
                }
                BaseDao.closeResource(null, pstm, rs);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    //修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException {

        PreparedStatement pstm = null;
        int execute = 0;

        if(connection!=null){
            String sql = "update smbms_user set userPassword =? where id =?";
            Object params[] = {password, id};
            BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null,pstm,null);
        }
        return execute;
    }


}
