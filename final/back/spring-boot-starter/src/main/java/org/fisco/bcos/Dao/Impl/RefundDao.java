package org.fisco.bcos.Dao.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Interface.IBalanceDao;
import org.fisco.bcos.Dao.Interface.IRefundDao;
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

import java.math.BigInteger;

@Repository
public class RefundDao implements IRefundDao {
    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;

    @Override
    public JSONObject refund(String orderid, int op) throws Exception {
        Credentials credentials = GenCredential.create(variables.getAdmin());
        OrderContract orderContract = OrderContract.load(variables.getOrder(), web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        TransactionReceipt transactionReceipt;

        if (op == 1)
            transactionReceipt = orderContract.rejectReturn(orderid).send();
        else
            transactionReceipt = orderContract.acceptReturn(orderid).send();


        JSONObject send = new JSONObject();

        if (transactionReceipt.getStatus().equals("0x0")){
            send.put("checked",true);
        }
        else if (transactionReceipt.getStatus().equals("0x16")){
            orderContract.rejectReturn(orderid).send();
            send.put("checked",false);
        }
        return send;
    }
}
