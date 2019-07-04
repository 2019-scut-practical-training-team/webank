package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IIfCreatedDao {
    public JSONObject ifCreated(String key)throws Exception;
}
