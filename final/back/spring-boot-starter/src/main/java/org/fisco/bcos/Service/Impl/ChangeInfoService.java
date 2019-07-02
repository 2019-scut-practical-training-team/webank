package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IChangeInfoService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service(value = "changeInfoService")
public class ChangeInfoService implements IChangeInfoService {
    @Autowired
    private Web3j web3j;
    @Autowired
    private Variables variables;

    @Override
    public JSONObject changeInfo(String key, String petId, String petType, int petPrice,
                                 String petName, String petImg, String petIntro)throws Exception {
        Credentials credentials = GenCredential.create(key);

        Market market = Market.load(variables.getMarket(), web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        TransactionReceipt transactionReceipt = market.changePetInfo(petId, petName, petType, BigInteger.valueOf(petPrice), petImg, petIntro).send();

        String status = transactionReceipt.getStatus();

        JSONObject object = new JSONObject();

        if (status.equals("0x0"))
            object.put("checked",true);

        else if (status.equals("0x16"))
            object.put("checked",false);

        return object;
    }
}
