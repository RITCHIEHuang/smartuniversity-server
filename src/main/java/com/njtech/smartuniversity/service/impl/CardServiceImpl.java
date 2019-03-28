package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.CardBean;
import com.njtech.smartuniversity.bean.CardBeanExample;
import com.njtech.smartuniversity.dao.CardBeanMapper;
import com.njtech.smartuniversity.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jimmy on 22:40 2018/6/29
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CardBeanMapper mCardBeanMapper;


    /**
     * 根据卡号id 查询一卡通信息
     *
     * @param id
     * @return
     */
    @Override
    public CardBean selectByCardId(int id) {
        return mCardBeanMapper.selectByPrimaryKey(id);
    }

    @Override
    public CardBean selectBySQId(String sqID) {
        CardBeanExample cardBeanExample = new CardBeanExample();
        CardBeanExample.Criteria criteria = cardBeanExample.createCriteria();
        criteria.andSqIdEqualTo(sqID);
        List<CardBean> list = mCardBeanMapper.selectByExample(cardBeanExample);
        if (list != null && list.size() > 0)
            return list.get(0);
        return null;
    }

    /**
     * 修改一卡通信息
     *
     * @param cardBean
     * @return
     */
    @Override
    public CardBean update(CardBean cardBean) {
        int id = cardBean.getId();
        mCardBeanMapper.updateByPrimaryKey(cardBean);
        return mCardBeanMapper.selectByPrimaryKey(id);
    }


    /**
     * 获取一卡通列表
     *
     * @return
     */
    @Override
    public List<CardBean> getAllCards() {
        CardBeanExample cardBeanExample = new CardBeanExample();
        CardBeanExample.Criteria criteria = cardBeanExample.createCriteria();
        criteria.andIdIsNotNull();
        return mCardBeanMapper.selectByExample(cardBeanExample);
    }

    @Override
    public List<CardBean> getAllUnpassCards() {
        CardBeanExample cardBeanExample = new CardBeanExample();
        CardBeanExample.Criteria criteria = cardBeanExample.createCriteria();
        criteria.andStateEqualTo("0");
        return mCardBeanMapper.selectByExample(cardBeanExample);
    }

    @Override
    public long getUnpassCount() {
        CardBeanExample cardBeanExample = new CardBeanExample();
        CardBeanExample.Criteria criteria = cardBeanExample.createCriteria();
        criteria.andStateEqualTo("0");
        return mCardBeanMapper.countByExample(cardBeanExample);
    }

    @Override
    public long getTotalCount() {
        CardBeanExample cardBeanExample = new CardBeanExample();
        CardBeanExample.Criteria criteria = cardBeanExample.createCriteria();
        criteria.andIdIsNotNull();
        return mCardBeanMapper.countByExample(cardBeanExample);
    }


    @Override
    public PageInfo<CardBean> getCardsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CardBean> list = getAllCards();
        return new PageInfo<>(list);
    }

    /**
     * 删除一卡通用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteById(int id) {
        int result = mCardBeanMapper.deleteByPrimaryKey(id);
        return result != 0;
    }

    /**
     * 新增一卡通信息
     *
     * @param cardBean
     * @return
     */
    @Override
    public CardBean addCard(CardBean cardBean) {
        mCardBeanMapper.insert(cardBean);
        CardBeanExample example = new CardBeanExample();
        CardBeanExample.Criteria criteria = example.createCriteria();
        criteria.andCardVidEqualTo(cardBean.getCardVid());
        List<CardBean> cardBeanList = mCardBeanMapper.selectByExample(example);
        if (cardBeanList != null && cardBeanList.size() > 0) {
            cardBean = cardBeanList.get(0);
            return cardBean;
        }
        return null;
    }
}
