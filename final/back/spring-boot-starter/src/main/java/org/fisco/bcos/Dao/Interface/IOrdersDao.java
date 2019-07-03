package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IOrdersDao {
    public JSONObject orders() throws Exception;
}
