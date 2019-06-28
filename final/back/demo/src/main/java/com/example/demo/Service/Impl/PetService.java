package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IPetService;
import org.springframework.stereotype.Service;

@Service(value = "petService")
public class PetService implements IPetService {
    @Override
    public JSONObject pets(){
        JSONObject send = new JSONObject();
        return send;
    }
}
