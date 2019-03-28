package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.GoodsBean;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomOrdersBeanMapper {


    @Select("select g.* from goods as g, orders as o where o.g_id=g.id group by g.id order by sum(o.num) desc")
    @ResultMap("com.njtech.smartuniversity.dao.GoodsBeanMapper.BaseResultMap")
    List<GoodsBean> getHotGoods();

}