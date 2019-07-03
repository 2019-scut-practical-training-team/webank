package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Service.Interface.IRefundService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "refundService")
public class RefundService implements IRefundService {
    @Autowired
    private Variables variables;
    @Autowired
    private Web3j web3j;


    @Override
    public JSONObject refund(String orderid, int op) throws Exception{
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
            send.put("checked",false);
        }
        else
            send.put("checked","error"+transactionReceipt.getStatus());
        return send;
    }
}
