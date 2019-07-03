package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Dao.Interface.ISigninDao;
import org.fisco.bcos.Variables;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.ISigninService;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;


@Service(value = "siService")
public class SigninService implements ISigninService {
    @Autowired
    private ISigninDao signinDao;


    @Override
    public JSONObject signin(String key) throws Exception{

        try {
            return signinDao.getIdentity(key);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("address", "none");
            object.put("identity", -1);
            return object;
        }
    }
}
