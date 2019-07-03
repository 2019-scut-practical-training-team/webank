package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Impl.ChangeInfoDao;
import org.fisco.bcos.Service.Interface.IChangeInfoService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;

@Service(value = "changeInfoService")
public class ChangeInfoService implements IChangeInfoService {
    @Autowired
    private ChangeInfoDao changeInfoDao;

    @Override
    public JSONObject changeInfo(String key, String petId, String petType, int petPrice,
                                 String petName, String petImg, String petIntro)throws Exception {
        try {
            TransactionReceipt transactionReceipt = changeInfoDao.changeInfo(key, petId, petType, petPrice, petName, petImg, petIntro);

            String status = transactionReceipt.getStatus();

            JSONObject object = new JSONObject();

            if (status.equals("0x0"))
                object.put("checked",true);

            else if (status.equals("0x16"))
                object.put("checked",false);


            return object;

        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("checked", "error");
            return object;
        }
    }
}
