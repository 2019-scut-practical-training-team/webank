package org.fisco.bcos.Dao.Interface;

import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface IChangeInfoDao {
    public TransactionReceipt changeInfo(String key, String petId, String petType, int petPrice,
                                         String petName, String petImg, String petIntro) throws Exception;
}
