package com.exch.response;

import java.util.List;

/**
 * @Description TickersResponse
 * @auther: xxx
 * @date: 20190906
 */
public class TickersResponse {

    private List<Tickers> data;

    public List<Tickers> getData() {
        return data;
    }

    public void setData(List<Tickers> data) {
        this.data = data;
    }
}
