package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IPetlistService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetlistService implements IPetlistService {


    @Autowired
    private Web3j web3j;


    @Override
    public JSONObject getPetlist(String key) throws Exception{

        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        String contract = "0x3d7bfc7b9cca1a7a78c23ac90fe165cb9f2d8a19";

        Market market = Market.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        String s = market.getPetListFromAddress().send();
        System.out.println(s);


        JSONObject object = new JSONObject();
        object.put("petsList",s);
        return object;
    }
}
