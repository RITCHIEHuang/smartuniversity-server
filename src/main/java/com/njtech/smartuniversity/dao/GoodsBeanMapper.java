package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.bean.GoodsBeanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsBeanMapper {
    long countByExample(GoodsBeanExample example);

    int deleteByExample(GoodsBeanExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsBean record);

    int insertSelective(GoodsBean record);

    List<GoodsBean> selectByExample(GoodsBeanExample example);

    GoodsBean selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsBean record, @Param("example") GoodsBeanExample example);

    int updateByExample(@Param("record") GoodsBean record, @Param("example") GoodsBeanExample example);

    int updateByPrimaryKeySelective(GoodsBean record);

    int updateByPrimaryKey(GoodsBean record);
}