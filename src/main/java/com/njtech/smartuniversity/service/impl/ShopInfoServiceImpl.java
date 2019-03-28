package com.njtech.smartuniversity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.njtech.smartuniversity.bean.ShopInfoBean;
import com.njtech.smartuniversity.bean.ShopInfoBeanExample;
import com.njtech.smartuniversity.dao.ShopInfoBeanMapper;
import com.njtech.smartuniversity.service.ShopInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jimmy on 22:42 2018/6/29
 */
@Service
public class ShopInfoServiceImpl implements ShopInfoService {

    @Autowired
    ShopInfoBeanMapper mShopInfoBeanMapper;


    /**
     * 根据商家名查询商家信息
     *
     * @param
     * @return
     */
    @Override
    public ShopInfoBean selectByShopInfoName(String name) {
        ShopInfoBeanExample userBeanExample = new ShopInfoBeanExample();
        ShopInfoBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andShopNameEqualTo(name);
        List<ShopInfoBean> userBeanList = mShopInfoBeanMapper.selectByExample(userBeanExample);
        if (userBeanList.size() > 0)
            return userBeanList.get(0);
        return null;
    }

    @Override
    public ShopInfoBean selectByShopInfoUUID(String uuid) {
        return mShopInfoBeanMapper.selectByPrimaryKey(uuid);
    }

    /**
     * 修改商家信息
     *
     * @param shopInfoBean
     * @return
     */
    @Override
    public ShopInfoBean update(ShopInfoBean shopInfoBean) {
        String uuid = shopInfoBean.getUuid();
        mShopInfoBeanMapper.updateByPrimaryKey(shopInfoBean);
        return mShopInfoBeanMapper.selectByPrimaryKey(uuid);
    }

    /**
     * 获取商家信息
     *
     * @return
     */
    @Override
    public List<ShopInfoBean> getAllShopInfos() {
        ShopInfoBeanExample userBeanExample = new ShopInfoBeanExample();
        ShopInfoBeanExample.Criteria criteria = userBeanExample.createCriteria();
        criteria.andUuidIsNotNull();
        List<ShopInfoBean> shopInfoBeanList = mShopInfoBeanMapper.selectByExample(userBeanExample);
        return shopInfoBeanList;
    }

    @Override
    public PageInfo<ShopInfoBean> getShopInfosByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ShopInfoBean> list = getAllShopInfos();
        return new PageInfo<>(list);
    }

    /**
     * 删除商家信息
     *
     * @param uuid
     * @return
     */
    @Override
    public boolean deleteByUUId(String uuid) {
        int result = mShopInfoBeanMapper.deleteByPrimaryKey(uuid);
        return result != 0;
    }

    /**
     * 新增商家信息
     *
     * @param shopInfoBean
     * @return
     */
    @Override
    public ShopInfoBean addShopInfoBean(ShopInfoBean shopInfoBean) {
        mShopInfoBeanMapper.insert(shopInfoBean);
        ShopInfoBeanExample example = new ShopInfoBeanExample();
        ShopInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andUuidEqualTo(shopInfoBean.getUuid());
        List<ShopInfoBean> shopInfoBeanList = mShopInfoBeanMapper.selectByExample(example);
        if (shopInfoBeanList != null && shopInfoBeanList.size() > 0) {
            shopInfoBean = shopInfoBeanList.get(0);
            return shopInfoBean;
//            return mCardBeanMapper.selectByPrimaryKey(cardBean.getId());
        }
        return null;
    }

    @Override
    public List<ShopInfoBean> selectbyUserUUid(String uuid) {
        ShopInfoBeanExample example = new ShopInfoBeanExample();
        ShopInfoBeanExample.Criteria criteria = example.createCriteria();
        criteria.andUserUuidEqualTo(uuid);
        List<ShopInfoBean> shopInfoBeanList = mShopInfoBeanMapper.selectByExample(example);
        return shopInfoBeanList;
    }


}
