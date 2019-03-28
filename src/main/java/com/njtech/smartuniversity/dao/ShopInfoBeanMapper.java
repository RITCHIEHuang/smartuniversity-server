package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.ShopInfoBean;
import com.njtech.smartuniversity.bean.ShopInfoBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopInfoBeanMapper {
    long countByExample(ShopInfoBeanExample example);

    int deleteByExample(ShopInfoBeanExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(ShopInfoBean record);

    int insertSelective(ShopInfoBean record);

    List<ShopInfoBean> selectByExample(ShopInfoBeanExample example);

    ShopInfoBean selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") ShopInfoBean record, @Param("example") ShopInfoBeanExample example);

    int updateByExample(@Param("record") ShopInfoBean record, @Param("example") ShopInfoBeanExample example);

    int updateByPrimaryKeySelective(ShopInfoBean record);

    int updateByPrimaryKey(ShopInfoBean record);
}