package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CardBean;
import com.njtech.smartuniversity.bean.CardBeanExample;
import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.bean.GoodsBeanExample;
import com.njtech.smartuniversity.dao.CustomGoodsBeanMapper;
import com.njtech.smartuniversity.dao.GoodsBeanMapper;
import com.njtech.smartuniversity.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jimmy on 22:41 2018/6/29
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsBeanMapper mGoodsBeanMapper;

    @Autowired
    CustomGoodsBeanMapper mCustomGoodsBeanMapper;


    /**
     * 根据商品id查询商品信息
     *
     * @param id
     * @return
     */
    @Override
    public GoodsBean selectByGoodsID(int id) {
        return mGoodsBeanMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改商品信息
     *
     * @param goodsBean
     * @return
     */
    @Override
    public GoodsBean update(GoodsBean goodsBean) {
        int id = goodsBean.getId();
        mGoodsBeanMapper.updateByPrimaryKey(goodsBean);
        return mGoodsBeanMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有商品信息列表
     *
     * @return
     */
    @Override
    public List<GoodsBean> getAllGoods() {
        GoodsBeanExample example = new GoodsBeanExample();
        GoodsBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        return mGoodsBeanMapper.selectByExample(example);
    }

    @Override
    public List<GoodsBean> getAllAvaliableGoods() {
        GoodsBeanExample example = new GoodsBeanExample();
        GoodsBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        criteria.andGStateEqualTo("1");
        return mGoodsBeanMapper.selectByExample(example);
    }

    @Override
    public List<GoodsBean> getShopGoods(String uuid) {
        GoodsBeanExample example = new GoodsBeanExample();
        GoodsBeanExample.Criteria criteria = example.createCriteria();
        criteria.andSIUuidEqualTo(uuid);
        return mGoodsBeanMapper.selectByExample(example);
    }

    @Override
    public List<GoodsBean> getMerchantGoods(String user_uuid) {
        return mCustomGoodsBeanMapper.getMerchantGoods(user_uuid);
    }

    @Override
    public PageInfo<GoodsBean> getGoodsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsBean> list = getAllGoods();
        return new PageInfo<>(list);
    }

    /**
     * 删除商品信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(int id) {
        int result = mGoodsBeanMapper.deleteByPrimaryKey(id);
        return result != 0;
    }

    /**
     * 增加商品信息
     *
     * @param goodsBean
     * @return
     */
    @Override
    public GoodsBean addGoods(GoodsBean goodsBean) {
        mGoodsBeanMapper.insert(goodsBean);
        GoodsBeanExample example = new GoodsBeanExample();
        GoodsBeanExample.Criteria criteria = example.createCriteria();
        criteria.andGNameEqualTo(goodsBean.getgName());
        List<GoodsBean> goodsBeanList = mGoodsBeanMapper.selectByExample(example);
        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            goodsBean = goodsBeanList.get(0);
            return goodsBean;
//            return mCardBeanMapper.selectByPrimaryKey(cardBean.getId());
        }
        return null;
    }
}
