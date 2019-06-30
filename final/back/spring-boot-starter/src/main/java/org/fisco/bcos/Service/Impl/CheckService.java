package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.ICheckService;
import org.springframework.stereotype.Service;

@Service(value = "checkService")
public class CheckService implements ICheckService {
    @Override
    public JSONObject check(String address){
        JSONObject send = new JSONObject();
        return send;
    }
}
