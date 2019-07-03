package org.fisco.bcos.Dao.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Interface.ICreatePetDao;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public class CreatePetDao implements ICreatePetDao {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Variables variables;

    @Override
    public TransactionReceipt createPet(String key, PetsListItem item) throws Exception {
        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create(key);


        Market market = Market.load(
                variables.getMarket(),
                web3j,
                credentials,
                new StaticGasProvider(
                        GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        TransactionReceipt transactionReceipt = market.createPet(item.getPetType(), BigInteger.valueOf(item.getPetPrice()),item.getPetName(),item.getPetImg(), item.getPetIntro()).send();

        return transactionReceipt;

    }
}
