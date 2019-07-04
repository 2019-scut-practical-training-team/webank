package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IBuyService;
import org.springframework.stereotype.Service;

@Service(value = "buyService")
public class BuyService implements IBuyService {
    @Override
    public JSONObject buy(String address, String petId, int petprice, String owner) {
        JSONObject send = new JSONObject();
        return send;
    }
}
