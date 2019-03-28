package com.njtech.smartuniversity.service;

import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CardBean;

import java.util.List;

public interface CardService {
    /**
     * 根据卡号id 查询一卡通信息
     * @param id
     * @return
     */
    CardBean selectByCardId(int id);


    CardBean selectBySQId(String sqID);
    /**
     * 修改一卡通信息
     * @param cardBean
     * @return
     */
    CardBean update(CardBean cardBean);

    /**
     * 获取一卡通列表
     *
     * @return
     */
    List<CardBean> getAllCards();



    List<CardBean> getAllUnpassCards();



    long getUnpassCount();

    long getTotalCount();


    /**
     * 获取分页的一卡通信息列表
     *
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return
     */
    PageInfo<CardBean> getCardsByPage(int pageNum, int pageSize);

    /**
     * 删除一卡通用户
     * @param id
     * @return
     */
    boolean deleteById(int id);

    /**
     * 新增一卡通信息
     * @param cardBean
     * @return
     */
    CardBean addCard(CardBean cardBean);
}
