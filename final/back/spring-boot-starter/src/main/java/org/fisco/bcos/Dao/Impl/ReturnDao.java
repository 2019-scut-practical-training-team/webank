package org.fisco.bcos.Dao.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Interface.IReturnDao;
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
public class ReturnDao implements IReturnDao {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;

    @Override
    public TransactionReceipt applyReturn(String key, int orderId, String reason) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        OrderContract orderContract = OrderContract.load(
                variables.getOrder(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));


        TransactionReceipt transactionReceipt = orderContract.applyForReturn(String.valueOf(orderId),reason).send();
        return transactionReceipt;
    }
}
