package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.CardBean;
import com.njtech.smartuniversity.bean.CardBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardBeanMapper {
    long countByExample(CardBeanExample example);

    int deleteByExample(CardBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CardBean record);

    int insertSelective(CardBean record);

    List<CardBean> selectByExample(CardBeanExample example);

    CardBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CardBean record, @Param("example") CardBeanExample example);

    int updateByExample(@Param("record") CardBean record, @Param("example") CardBeanExample example);

    int updateByPrimaryKeySelective(CardBean record);

    int updateByPrimaryKey(CardBean record);
}