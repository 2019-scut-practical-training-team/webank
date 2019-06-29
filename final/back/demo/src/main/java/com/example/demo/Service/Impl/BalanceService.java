package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IBalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceService implements IBalanceService {
    public JSONObject getBalance(String address) {
        JSONObject object = new JSONObject();
        object.put("balance",20000);
        return object;
    }
}
