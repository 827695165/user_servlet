package com.cs.service;

import com.cs.entity.User;

import java.util.List;

public interface UserService {

    //查询数据的总记录数
    public int findByCountUser()throws Exception;

    //分页方法
    public List<User> findByPageUserInfo(int pageNo, int pageSize) throws Exception;

    //显示用户信息
    public List<User> showUser() throws Exception;

    //添加用户信息
    public boolean addUser(User us ) throws Exception;

    //根据id查看用户信息
    public User findByUserInfo(int id) throws Exception;

    //根据id修改用户信息
    public boolean updateUser(User us) throws Exception;

    //根据id删除用户信息
    public boolean delUser(int id) throws Exception;
}
