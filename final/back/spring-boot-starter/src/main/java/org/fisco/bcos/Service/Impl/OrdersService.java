package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IOrdersService;
import org.springframework.stereotype.Service;

@Service(value = "ordersService")
public class OrdersService implements IOrdersService {
    @Override
    public JSONObject orders(){
        JSONObject send = new JSONObject();
        return send;
    }
}
