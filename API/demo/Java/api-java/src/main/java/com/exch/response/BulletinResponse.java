package com.exch.response;

import java.util.List;

/**
 * @Description BulletinResponse
 * @auther: xxx
 * @date: 20190919
 */
public class BulletinResponse extends BaseEntity{

    List<BulletinPo> data;

    public List<BulletinPo> getData() {
        return data;
    }

    public void setData(List<BulletinPo> data) {
        this.data = data;
    }
}
