package com.example.demo.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface ISellService {
    public JSONObject sell(String address, int petId);
}
