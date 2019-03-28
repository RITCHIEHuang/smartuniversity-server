package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CustomGoodsBean;
import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.bean.OrderBean;
import com.njtech.smartuniversity.bean.OrderBeanExample;
import com.njtech.smartuniversity.dao.CustomOrdersBeanMapper;
import com.njtech.smartuniversity.dao.GoodsBeanMapper;
import com.njtech.smartuniversity.dao.OrderBeanMapper;
import com.njtech.smartuniversity.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jimmy on 22:41 2018/6/29
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderBeanMapper mOrderBeanMapper;


    @Autowired
    GoodsBeanMapper mGoodsBeanMapper;

    @Autowired
    CustomOrdersBeanMapper mCustomOrdersBeanMapper;


    /**
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    @Override
    public OrderBean selecteByOrderId(int id) {
        return mOrderBeanMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     * @return
     */
    @Override
    public OrderBean update(OrderBean orderBean) {
        int id = orderBean.getgId();
        mOrderBeanMapper.updateByPrimaryKey(orderBean);
        return mOrderBeanMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取所有订单信息列表
     *
     * @return
     */
    @Override
    public List<OrderBean> getAllOrders() {
        OrderBeanExample example = new OrderBeanExample();
        OrderBeanExample.Criteria criteria = example.createCriteria();
        criteria.andIdIsNotNull();
        List<OrderBean> list = mOrderBeanMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<OrderBean> getUserOrders(String user_uuid) {
        OrderBeanExample example = new OrderBeanExample();
        OrderBeanExample.Criteria criteria = example.createCriteria();
        criteria.andUserUuidEqualTo(user_uuid);
        return mOrderBeanMapper.selectByExample(example);
    }

    @Override
    public PageInfo<OrderBean> getOrdersByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<OrderBean> list = getAllOrders();
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<CustomGoodsBean> getHotOrdersByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsBean> list = mCustomOrdersBeanMapper.getHotGoods();
        List<CustomGoodsBean> customGoodsBeanList = new ArrayList<>(list.size());
        for (int i = 0; i < list.size(); i++) {
            CustomGoodsBean goodsBean = new CustomGoodsBean();
            goodsBean.setGoodsBean(list.get(i));
            goodsBean.setRank(i + 1);
            customGoodsBeanList.add(goodsBean);
        }
        return new PageInfo<>(customGoodsBeanList);
    }


    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(int id) {
        int result = mOrderBeanMapper.deleteByPrimaryKey(id);
        return result != 0;
    }

    /**
     * 新增订单
     *
     * @param orderBean
     * @return
     */
    @Override
    public OrderBean addOrderBean(OrderBean orderBean) {
        mOrderBeanMapper.insert(orderBean);
        OrderBeanExample example = new OrderBeanExample();
        OrderBeanExample.Criteria criteria = example.createCriteria();
        criteria.andUserUuidEqualTo(orderBean.getUserUuid());
        criteria.andGIdEqualTo(orderBean.getgId());
        int good_id = orderBean.getgId();
        GoodsBean goodsBean = mGoodsBeanMapper.selectByPrimaryKey(good_id);
        int num = goodsBean.getgQuantity();
        int new_num = num - orderBean.getNum();
        if (new_num < 0) {
            return null;
        } else if (new_num == 0) {
            //缺货
            goodsBean.setgState("-1");
        }
        goodsBean.setgQuantity(new_num);
        mGoodsBeanMapper.updateByPrimaryKey(goodsBean);
        List<OrderBean> orderBeanList = mOrderBeanMapper.selectByExample(example);
        if (orderBeanList != null && orderBeanList.size() > 0) {
            orderBean = orderBeanList.get(0);
            return orderBean;
//            return mCardBeanMapper.selectByPrimaryKey(cardBean.getId());
        }
        return null;
    }
}
