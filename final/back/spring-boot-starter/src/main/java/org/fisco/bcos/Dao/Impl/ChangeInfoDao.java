package org.fisco.bcos.Dao.Impl;

import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Interface.IBuyDao;
import org.fisco.bcos.Dao.Interface.IChangeInfoDao;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;

@Repository
public class ChangeInfoDao implements IChangeInfoDao {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;


    @Override
    public TransactionReceipt changeInfo(String key, String petId, String petType, int petPrice,
                                         String petName, String petImg, String petIntro) throws Exception {
        Credentials credentials = GenCredential.create(key);

        Market market = Market.load(variables.getMarket(), web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        TransactionReceipt transactionReceipt = market.changePetInfo(petId, petName, petType, BigInteger.valueOf(petPrice), petImg, petIntro).send();

        return transactionReceipt;
    }
}
