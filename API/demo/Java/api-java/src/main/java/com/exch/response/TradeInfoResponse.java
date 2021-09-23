package com.exch.response;

import java.util.List;

/**
 * @Description TradeInfoResponse
 * @auther: xxx
 * @date: 20190906
 */
public class TradeInfoResponse {

    private List<TradeInfo> data; //交易对信息

    public List<TradeInfo> getData() {
        return data;
    }

    public void setData(List<TradeInfo> data) {
        this.data = data;
    }
}
