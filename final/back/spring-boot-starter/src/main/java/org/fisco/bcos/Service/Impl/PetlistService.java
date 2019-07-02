package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.*;
import org.fisco.bcos.Service.Interface.IPetlistService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class PetlistService implements IPetlistService {


    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;


    @Override
    public JSONObject getPetlist(String key) throws Exception{

        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);


        Market market = Market.load(
                variables.getMarket(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        //BigInteger[] indexs = market.getIndex().send;
//        PetsListItem[] list;
//        for(int i=0;i<index.length;i++){
//               list[i]=market.getpet();
//        }
        String s = market.getPetListFromAddress().send();



        JSONObject object = new JSONObject();
        object.put("petsList",s);
        return object;
    }
}
