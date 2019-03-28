package com.njtech.smartuniversity;

import com.njtech.smartuniversity.bean.GoodsBean;
import com.njtech.smartuniversity.dao.CustomGoodsBeanMapper;
import com.njtech.smartuniversity.dao.CustomOrdersBeanMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomGoodsMapperTest {

    @Autowired
    private CustomGoodsBeanMapper mCustomGoodsBeanMapper;

    @Autowired
    private CustomOrdersBeanMapper mCustomOrdersBeanMapper;

    @Test
    public void getMerchatGoods() throws Exception {
        mCustomGoodsBeanMapper.getMerchantGoods("afca81b91ceb493b969223ace6332627");
    }


    @Test
    public void getHotGoods() throws Exception {
        mCustomOrdersBeanMapper.getHotGoods();
    }
}
