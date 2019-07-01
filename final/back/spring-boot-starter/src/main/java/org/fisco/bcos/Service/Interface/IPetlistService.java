package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IPetlistService {
    public JSONObject getPetlist(String address)throws Exception;

}
