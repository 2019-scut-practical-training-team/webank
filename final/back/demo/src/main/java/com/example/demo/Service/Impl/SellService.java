package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.ISellService;
import org.springframework.stereotype.Service;

@Service
public class SellService implements ISellService {
    @Override
    public JSONObject sell(String address, int petId) {
        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
