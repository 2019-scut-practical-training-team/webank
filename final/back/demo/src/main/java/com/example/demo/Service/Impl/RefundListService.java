package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IRefundListService;
import org.springframework.stereotype.Service;

@Service(value = "refundListService")
public class RefundListService implements IRefundListService {
    @Override
    public JSONObject refundList(){
        JSONObject send = new JSONObject();
        return send;
    }
}
