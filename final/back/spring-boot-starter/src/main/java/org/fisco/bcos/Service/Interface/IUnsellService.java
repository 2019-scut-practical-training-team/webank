package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IUnsellService {
    public JSONObject unsell(String address, int petId)throws Exception;
}
