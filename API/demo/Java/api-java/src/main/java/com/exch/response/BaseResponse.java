package com.exch.response;

/**
 * @Description base Response
 * @auther: xxx
 * @date: 20190906
 */
public class BaseResponse {
    private String status;//状态
    public String message;//信息

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
