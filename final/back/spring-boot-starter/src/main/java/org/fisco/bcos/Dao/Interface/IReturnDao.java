package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IReturnDao {
    public TransactionReceipt applyReturn(String key, int orderId, String reason) throws Exception;
}
