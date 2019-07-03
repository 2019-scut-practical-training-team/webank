package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Service.Interface.IReturnService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.solidity.Abi;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service(value = "returnService")
public class ReturnService implements IReturnService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;

    @Override
    public JSONObject returnOrder(String key, int orderId,String reason) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        OrderContract orderContract = OrderContract.load(
                variables.getOrder(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));


        TransactionReceipt transactionReceipt = orderContract.applyForReturn(String.valueOf(orderId),reason).send();
        String status = transactionReceipt.getStatus();

        JSONObject object = new JSONObject();

        if (status.equals("0x0"))
            object.put("checked",true);

        else if (status.equals("0x16"))
            object.put("checked",false);

        return object;

    }
}
