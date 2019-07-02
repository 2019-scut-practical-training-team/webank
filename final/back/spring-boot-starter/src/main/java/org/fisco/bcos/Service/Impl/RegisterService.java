package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IRegisterService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;

    @Override
    public JSONObject register()  throws Exception{
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);

        Market market = Market.load(
                variables.getMarket(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        JSONObject object = new JSONObject();

        try {
            market.createUser().send();

            object.put("checked", true);
            object.put("key", privateKey);
            return object;
        }
        catch (Exception e){
            System.out.println("Register failed!");
            object.put("checked", false);
            object.put("key", "none");
            return object;
        }
    }
}
