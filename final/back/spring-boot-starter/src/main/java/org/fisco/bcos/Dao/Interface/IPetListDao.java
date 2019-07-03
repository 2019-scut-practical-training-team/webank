package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IPetListDao {
    public JSONObject petList(String key)throws Exception;
}
