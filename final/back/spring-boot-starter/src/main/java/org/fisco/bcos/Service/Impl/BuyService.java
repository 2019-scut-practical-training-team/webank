package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IBuyService;
import org.springframework.stereotype.Service;

@Service(value = "buyService")
public class BuyService implements IBuyService {
    @Override
    public JSONObject buy(String key, String petId) {
        JSONObject send = new JSONObject();
        return send;
    }
}
