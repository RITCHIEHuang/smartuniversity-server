package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.AuthorityBean;
import com.njtech.smartuniversity.bean.AuthorityBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityBeanMapper {
    long countByExample(AuthorityBeanExample example);

    int deleteByExample(AuthorityBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AuthorityBean record);

    int insertSelective(AuthorityBean record);

    List<AuthorityBean> selectByExample(AuthorityBeanExample example);

    AuthorityBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AuthorityBean record, @Param("example") AuthorityBeanExample example);

    int updateByExample(@Param("record") AuthorityBean record, @Param("example") AuthorityBeanExample example);

    int updateByPrimaryKeySelective(AuthorityBean record);

    int updateByPrimaryKey(AuthorityBean record);
}