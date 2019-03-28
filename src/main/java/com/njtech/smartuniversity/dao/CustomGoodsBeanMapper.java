package com.njtech.smartuniversity.dao;

import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.bean.GoodsBeanExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomGoodsBeanMapper {


    @Select("select * from goods where s_i_uuid in (select uuid from shop_info where user_uuid = #{userUUid})")
    @ResultMap("com.njtech.smartuniversity.dao.GoodsBeanMapper.BaseResultMap")
    List<GoodsBean> getMerchantGoods(@Param("userUUid") String userUUid);

}