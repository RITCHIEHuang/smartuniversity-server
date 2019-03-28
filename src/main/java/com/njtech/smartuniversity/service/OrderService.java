package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CustomGoodsBean;
import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.bean.OrderBean;
import org.springframework.core.annotation.Order;

import java.util.List;

public interface OrderService {
    /**
     * 根据id查询订单信息
     *
     * @param id
     * @return
     */
    OrderBean selecteByOrderId(int id);

    /**
     * 修改订单信息
     *
     * @return
     */
    OrderBean update(OrderBean orderBean);

    /**
     * 获取订单信息
     *
     * @return
     */
    List<OrderBean> getAllOrders();


    List<OrderBean> getUserOrders(String user_uuid);


    /**
     * 获取分页的订单信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo<OrderBean> getOrdersByPage(int pageNum, int pageSize);

    PageInfo<CustomGoodsBean> getHotOrdersByPage(int pageNum, int pageSize);

    /**
     * 删除订单信息
     *
     * @param id
     * @return
     */
    boolean deleteById(int id);

    /**
     * 新增订单信息
     *
     * @param orderBean
     * @return
     */
    OrderBean addOrderBean(OrderBean orderBean);
}
