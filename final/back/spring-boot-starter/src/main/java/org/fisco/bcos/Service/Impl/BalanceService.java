package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Interface.IBalanceDao;
import org.fisco.bcos.Service.Interface.IBalanceService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationEvent;
import java.beans.BeanInfo;
import java.math.BigInteger;

@Service
public class BalanceService implements IBalanceService {


    @Autowired
    private IBalanceDao balanceDao;


    public JSONObject getBalance(String key){

        try {
            return balanceDao.getBalance(key);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("balance", -1);
            return object;
        }
    }
}
