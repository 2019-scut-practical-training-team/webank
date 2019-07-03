package org.fisco.bcos.Dao.Interface;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IBuyDao {
    public TransactionReceipt buy(String key, String petId)throws Exception;
}
