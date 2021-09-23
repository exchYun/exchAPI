package com.exch.response;

import java.util.List;

/**
 * @Description MyEntrustOrderResponse
 * @auther: xxx
 * @date: 20190910
 */
public class MyEntrustOrderResponse {
    private List<EntrustOrder> data;

    public List<EntrustOrder> getData() {
        return data;
    }

    public void setData(List<EntrustOrder> data) {
        this.data = data;
    }
}
