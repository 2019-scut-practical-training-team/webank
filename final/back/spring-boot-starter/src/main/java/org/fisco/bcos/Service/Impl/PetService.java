package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IPetService;
import org.springframework.stereotype.Service;

@Service(value = "petService")
public class PetService implements IPetService {
    @Override
    public JSONObject pets(){
        JSONObject send = new JSONObject();
        return send;
    }
}
