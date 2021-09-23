package com.exch.response;

import java.util.List;

/**
 * @Description OpenOrderResponse
 * @auther: xxx
 * @date:
 */
public class OpenOrderResponse {
    private List<Order> data;

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
