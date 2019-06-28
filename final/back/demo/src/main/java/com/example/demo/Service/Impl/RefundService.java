package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IRefundService;
import org.springframework.stereotype.Service;

@Service(value = "refundService")
public class RefundService implements IRefundService {
    @Override
    public JSONObject refund(String orderid, int orderstatus){
        JSONObject send = new JSONObject();
        return send;
    }
}
