package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface ISigninDao {
    public JSONObject getIdentity(String key)throws Exception;
}
