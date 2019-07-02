package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.ICreatePetService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.crypto.gm.sm2.util.encoders.Hex;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.solidity.Abi;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Service
public class CreatePetService implements ICreatePetService {

    @Autowired
    private Web3j web3j;

    @Override
    public JSONObject createPet(String key, PetsListItem item) throws Exception{
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);

        String contract = "0xa7f3026b5b9274c7b96ba0c5bbbf3db866b2f3ed";

        Market market = Market.load(
                contract,
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        TransactionReceipt transactionReceipt = market.createPet(item.getPetName(),BigInteger.valueOf(item.getPetPrice()),item.getPetType(),item.getPetImg(), item.getPetIntro()).send();
        String logs  = market.getTransactionReceipt().toString();

        JSONObject object = new JSONObject();
        object.put("checked",true);

        return object;
    }
}
