package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.GoodsBean;

import java.util.List;

public interface GoodsService {
    /**
     * 根据商品id查询商品信息
     *
     * @param id
     * @return
     */
    GoodsBean selectByGoodsID(int id);

    /**
     * 修改商品信息
     *
     * @param goodsBean
     * @return
     */
    GoodsBean update(GoodsBean goodsBean);

    /**
     * 获取所有商品信息列表
     *
     * @return
     */
    List<GoodsBean> getAllGoods();


    List<GoodsBean> getAllAvaliableGoods();


    List<GoodsBean> getShopGoods(String uuid);


    List<GoodsBean> getMerchantGoods(String user_uuid);


    /**
     * 获取分页的商品信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo<GoodsBean> getGoodsByPage(int pageNum, int pageSize);


    /**
     * 删除商品信息
     *
     * @param id
     * @return
     */
    boolean deleteById(int id);

    /**
     * 增加商品信息
     *
     * @param goodsBean
     * @return
     */
    GoodsBean addGoods(GoodsBean goodsBean);
}
