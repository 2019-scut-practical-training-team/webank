package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.ICheckService;
import org.springframework.stereotype.Service;

@Service(value = "checkService")
public class CheckService implements ICheckService {
    @Override
    public JSONObject check(String address){
        JSONObject send = new JSONObject();
        return send;
    }
}
