package com.example.demo.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IBalanceService {
    public JSONObject getBalance(String address);
}
