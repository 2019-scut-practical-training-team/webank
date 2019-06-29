package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IBalanceService;
import org.springframework.stereotype.Service;

@Service
public class BalanceService implements IBalanceService {
    public JSONObject getBalance(String address) {
        JSONObject object = new JSONObject();
        object.put("balance",20000);
        return object;
    }
}
