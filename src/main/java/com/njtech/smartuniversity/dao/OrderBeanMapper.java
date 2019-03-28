package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.OrderBean;
import com.njtech.smartuniversity.bean.OrderBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderBeanMapper {
    long countByExample(OrderBeanExample example);

    int deleteByExample(OrderBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OrderBean record);

    int insertSelective(OrderBean record);

    List<OrderBean> selectByExample(OrderBeanExample example);

    OrderBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OrderBean record, @Param("example") OrderBeanExample example);

    int updateByExample(@Param("record") OrderBean record, @Param("example") OrderBeanExample example);

    int updateByPrimaryKeySelective(OrderBean record);

    int updateByPrimaryKey(OrderBean record);
}