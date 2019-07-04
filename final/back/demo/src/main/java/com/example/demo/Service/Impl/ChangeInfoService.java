package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IChangeInfoService;
import org.springframework.stereotype.Service;

@Service(value = "changeInfoService")
public class ChangeInfoService implements IChangeInfoService {
    @Override
    public JSONObject changeInfo(String address, String petId, String petType, int petPrice,
                                 String petName, String petImg, String petIntro){
        JSONObject send = new JSONObject();
        return send;
    }
}
