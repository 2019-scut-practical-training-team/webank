package com.example.demo.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IBuyService {
    public JSONObject buy(String address, String petId, int petprice, String owner);
}
