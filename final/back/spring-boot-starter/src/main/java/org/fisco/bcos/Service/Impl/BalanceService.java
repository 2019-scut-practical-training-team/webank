package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IBalanceService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.BeanInfo;
import java.math.BigInteger;

@Service
public class BalanceService implements IBalanceService {


    @Autowired
    private Web3j web3j;


    public JSONObject getBalance(String key) throws Exception{

        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        String contract = "16fc777b4401962f5398f82342af29e18427c077";

        Market market = Market.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        int balance = market.getBalanceOfMe().send().intValue();

        JSONObject object = new JSONObject();
        object.put("balance",balance);
        return object;
    }
}
