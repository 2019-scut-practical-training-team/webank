package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IBalanceDao {
    public JSONObject getBalance(String key)throws Exception;
}
