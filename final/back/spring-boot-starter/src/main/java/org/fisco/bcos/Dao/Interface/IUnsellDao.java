package org.fisco.bcos.Dao.Interface;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IUnsellDao {
    public TransactionReceipt unsell(String key, int petId)throws Exception;
}
