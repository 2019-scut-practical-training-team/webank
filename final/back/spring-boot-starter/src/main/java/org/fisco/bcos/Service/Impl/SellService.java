package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.ISellService;
import org.springframework.stereotype.Service;

@Service
public class SellService implements ISellService {
    @Override
    public JSONObject sell(String address, int petId) {
        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
