package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.LoginBean;
import com.njtech.smartuniversity.bean.UserBean;
import com.njtech.smartuniversity.util.JsonResult;

import java.util.List;

/**
 * Created by ritchie on 6/25/18
 */
public interface UserService {

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID（学号/教师工号）
     * @return
     */
    UserBean selectByUserId(String id);

    /**
     * 根据账号、密码查找用户
     *
     * @param id
     * @param password
     * @return
     */
    UserBean selectByIdAndPassword(String id, String password);


    /**
     * 根据邮箱密码查找用户
     *
     * @param email
     * @param password
     * @return
     */
    LoginBean loginByEmailAndPassword(String email, String password);


    /**
     * 根据账户密码查找用户
     *
     * @param account
     * @param password
     * @return
     */
    LoginBean loginByAccountAndPassword(String account, String password);

    /**
     * 根据账号、密码登录返回token
     *
     * @param id
     * @param password
     * @return
     */
    LoginBean login(String id, String password);


    /**
     * 修改用户信息
     *
     * @param userBean
     * @return
     */
    UserBean update(UserBean userBean);


    /**
     * 获取用户列表
     *
     * @return
     */
    List<UserBean> getAllUsers();


    /**
     * 获取分页的用户列表信息
     *
     * @param pageNum  页码
     * @param pageSize 每页的数量
     * @return
     */
    PageInfo<UserBean> getUsersByPage(int pageNum, int pageSize);


    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    boolean deleteById(String id);

    /**
     * 新增用户
     *
     * @param userBean
     * @return
     */
    UserBean addUser(UserBean userBean);

    /**
     * 注册
     *
     * @param userBean
     * @return
     */
    UserBean register(UserBean userBean);
}
