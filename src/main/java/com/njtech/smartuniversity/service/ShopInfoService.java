package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.ShopInfoBean;

import java.util.List;

public interface ShopInfoService {
    /**
     * 根据商家name查询商家信息
     *
     * @param name
     * @return
     */
    ShopInfoBean selectByShopInfoName(String name);

    /**
     * 根据商家uuid查询商家信息
     *
     * @param uuid
     * @return
     */
    ShopInfoBean selectByShopInfoUUID(String uuid);

    /**
     * 修改商家信息
     *
     * @param shopInfoBean
     * @return
     */
    ShopInfoBean update(ShopInfoBean shopInfoBean);

    /**
     * 获取所有商家信息列表
     *
     * @return
     */
    List<ShopInfoBean> getAllShopInfos();


    /**
     * 获取分页的商家信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo<ShopInfoBean> getShopInfosByPage(int pageNum, int pageSize);


    /**
     * 删除商家信息
     *
     * @param uuid
     * @return
     */
    boolean deleteByUUId(String uuid);

    /**
     * 新增商家信息
     *
     * @param shopInfoBean
     * @return
     */
    ShopInfoBean addShopInfoBean(ShopInfoBean shopInfoBean);

    List<ShopInfoBean> selectbyUserUUid(String uuid);
}
