package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IBuyService {
    public JSONObject buy(String key, String petId)throws Exception;
}
