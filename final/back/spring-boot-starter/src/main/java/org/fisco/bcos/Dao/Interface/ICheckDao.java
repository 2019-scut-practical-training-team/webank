package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface ICheckDao {
    public JSONObject check(String key) throws Exception;
}
