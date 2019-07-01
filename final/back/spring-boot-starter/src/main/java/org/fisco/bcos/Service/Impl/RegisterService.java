package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IRegisterService;
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
    @Override
    public JSONObject register() {
        JSONObject object = new JSONObject();


        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        String address = credentials.getAddress();
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);

        String contract = "16fc777b4401962f5398f82342af29e18427c077";
        Market market = Market.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));


        market.createUser();

        object.put("checked",true);
        object.put("key",privateKey);
        return object;
    }
}
