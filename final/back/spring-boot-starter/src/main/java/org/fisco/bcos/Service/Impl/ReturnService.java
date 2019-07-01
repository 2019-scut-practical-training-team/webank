package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Service.Interface.IReturnService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "returnService")
public class ReturnService implements IReturnService {

    @Autowired
    private Web3j web3j;

    @Override
    public JSONObject returnOrder(String key, int orderId,String reason) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        String contract = "16fc777b4401962f5398f82342af29e18427c077";

        OrderContract orderContract = OrderContract.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));


        orderContract.applyForReturn(String.valueOf(orderId),reason).send();

        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
