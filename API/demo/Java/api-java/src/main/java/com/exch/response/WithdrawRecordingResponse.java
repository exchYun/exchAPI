package com.exch.response;

import java.util.List;

/**
 * @Description 冲、提 Response
 * @auther: xxx
 * @date: 20190910
 */
public class WithdrawRecordingResponse {


    private List<WithdrawRecording> data;

    public List<WithdrawRecording> getData() {
        return data;
    }

    public void setData(List<WithdrawRecording> data) {
        this.data = data;
    }
}
