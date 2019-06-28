package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IOrdersService;
import org.springframework.stereotype.Service;

@Service(value = "ordersService")
public class OrdersService implements IOrdersService {
    @Override
    public JSONObject orders(){
        JSONObject send = new JSONObject();
        return send;
    }
}
