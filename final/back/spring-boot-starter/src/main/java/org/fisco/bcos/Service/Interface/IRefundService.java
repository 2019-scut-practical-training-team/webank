package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IRefundService {
    public JSONObject refund(String orderid, int op) throws Exception;
}
