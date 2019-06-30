package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IBalanceService {
    public JSONObject getBalance(String address);
}
