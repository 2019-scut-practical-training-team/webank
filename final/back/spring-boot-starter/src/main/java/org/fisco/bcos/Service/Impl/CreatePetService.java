package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.ICreatePetService;
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
public class CreatePetService implements ICreatePetService {

    @Autowired
    private Web3j web3j;

    @Override
    public JSONObject createPet(String key, PetsListItem item) throws Exception{
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        String contract = "0x3d7bfc7b9cca1a7a78c23ac90fe165cb9f2d8a19";

        Market market = Market.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        market.createPet(item.getPetName(),BigInteger.valueOf(item.getPetPrice()),item.getPetType(),item.getPetImg(), item.getPetIntro()).send();

        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
