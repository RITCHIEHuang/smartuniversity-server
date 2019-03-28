package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.AuthorityBean;

import java.util.List;

/**
 * Created by ritchie on 6/25/18
 */
public interface AuthorityService {
    /**
     * 根据权限ID查询权限
     *
     * @param id 权限ID
     * @return
     */
    AuthorityBean selectByAuthorityId(int id);


    /**
     * 修改权限信息
     *
     * @param authorityBean
     * @return
     */
    AuthorityBean update(AuthorityBean authorityBean);


    /**
     * 获取权限列表
     *
     * @return
     */
    List<AuthorityBean> getAllAuthorities();

    /**
     * 获取分页的权限列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo<AuthorityBean> getAuthorityByPage(int pageNum, int pageSize);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    boolean deleteById(int id);

    /**
     * 新增权限
     *
     * @param authorityBean
     * @return
     */
    AuthorityBean addAuthority(AuthorityBean authorityBean);
}
