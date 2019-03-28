package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.EmailInfoBean;
import com.njtech.smartuniversity.bean.EmailInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailInfoBeanMapper {
    long countByExample(EmailInfoBeanExample example);

    int deleteByExample(EmailInfoBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmailInfoBean record);

    int insertSelective(EmailInfoBean record);

    List<EmailInfoBean> selectByExample(EmailInfoBeanExample example);

    EmailInfoBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmailInfoBean record, @Param("example") EmailInfoBeanExample example);

    int updateByExample(@Param("record") EmailInfoBean record, @Param("example") EmailInfoBeanExample example);

    int updateByPrimaryKeySelective(EmailInfoBean record);

    int updateByPrimaryKey(EmailInfoBean record);
}