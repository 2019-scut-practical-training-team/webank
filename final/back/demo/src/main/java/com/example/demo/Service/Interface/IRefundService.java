package com.example.demo.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IRefundService {
    public JSONObject refund(String orderid, int orderstatus);
}
