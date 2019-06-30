package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.ISigninService;
import org.springframework.stereotype.Service;


@Service(value = "siService")
public class SigninService implements ISigninService {

    @Override
    public JSONObject signin(String key){
        JSONObject json = new JSONObject();
        json.put("check",true);
        json.put("address","ox123");
        json.put("identity",1);
        return json;
    }
}
