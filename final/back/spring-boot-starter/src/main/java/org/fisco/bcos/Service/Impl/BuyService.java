package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Impl.BuyDao;
import org.fisco.bcos.Service.Interface.IBuyService;
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
import java.util.Date;

@Service(value = "buyService")
public class BuyService implements IBuyService {
    @Autowired
    private BuyDao buyDao;

    @Override
    public JSONObject buy(String key, String petId) throws Exception{
        try {
            TransactionReceipt transactionReceipt = buyDao.buy(key, petId);

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
