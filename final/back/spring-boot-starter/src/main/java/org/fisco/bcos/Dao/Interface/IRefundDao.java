package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IRefundDao {
    public JSONObject refund(String orderid, int op) throws Exception;
}
