package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface ISigninService {
    public JSONObject signin(String key) throws Exception;
}
