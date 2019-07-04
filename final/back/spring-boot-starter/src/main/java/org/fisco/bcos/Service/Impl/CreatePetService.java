package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Interface.ICreatePetDao;
import org.fisco.bcos.Service.Interface.ICreatePetService;
import org.fisco.bcos.Variables;
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
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CreatePetService implements ICreatePetService {

    @Autowired
    private ICreatePetDao createPetDao;

    @Override
    public JSONObject createPet(String key, PetsListItem item) {


        try {
            TransactionReceipt transactionReceipt = createPetDao.createPet(key,item);
            String status = transactionReceipt.getStatus();
            JSONObject jsonObject = new JSONObject();
            if(status.equals("0x0")) {

                jsonObject.put("checked",true);
            }
            else
                jsonObject.put("checked",false);

            return jsonObject;
        }
        catch (Exception e){

            JSONObject object = new JSONObject();

            object.put("checked","error");

            return object;
        }
    }
}
