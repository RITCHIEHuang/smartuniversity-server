package com.njtech.smartuniversity.bean;

import java.io.Serializable;

public class CustomGoodsBean implements Serializable {
    int rank;

    GoodsBean mGoodsBean;

    public GoodsBean getGoodsBean() {
        return mGoodsBean;
    }

    public void setGoodsBean(GoodsBean goodsBean) {
        mGoodsBean = goodsBean;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}