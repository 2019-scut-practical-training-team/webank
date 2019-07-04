package org.fisco.bcos.Dao.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Interface.ISellDao;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SellDao implements ISellDao {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;


    @Override
    public TransactionReceipt sell(String key, int petId) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        Market market = Market.load(
                variables.getMarket(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));


        TransactionReceipt transactionReceipt =  market.sellPet(String.valueOf(petId)).send();

        return transactionReceipt;
    }
}
