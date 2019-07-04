package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IRefundListDao {
    public JSONObject refundList() throws Exception;
}
