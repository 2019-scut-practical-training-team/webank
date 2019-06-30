package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IUnsellService;
import org.springframework.stereotype.Service;

@Service
public class UnsellService implements IUnsellService {

    @Override
    public JSONObject unsell(String address, int petId) {
        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
