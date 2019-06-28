package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IRegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Override
    public JSONObject register() {
        JSONObject object = new JSONObject();
        object.put("key","0x123123");
        return object;
    }
}
