package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface ISellDao {
    public TransactionReceipt sell(String key, int petId)throws Exception;
}
