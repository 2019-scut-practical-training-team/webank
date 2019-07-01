package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IChangeInfoService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service(value = "changeInfoService")
public class ChangeInfoService implements IChangeInfoService {
    @Autowired
    private Web3j web3j;

    @Override
    public JSONObject changeInfo(String key, String petId, String petType, int petPrice,
                                 String petName, String petImg, String petIntro)throws Exception {
        String contract = "185159bfdd227ee90d947cc80446297e6130b8b5";
        Credentials credentials = GenCredential.create(key);
        Market market = Market.load(contract, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        market.changePetInfo(petName, petId, petType, BigInteger.valueOf(petPrice), petImg, petIntro).send();

        // TODO
        JSONObject send = new JSONObject();
        send.put("checked", true);
        return send;
    }
}
