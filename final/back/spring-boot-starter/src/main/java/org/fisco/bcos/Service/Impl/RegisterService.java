package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Dao.Impl.RegisterDao;
import org.fisco.bcos.Dao.Interface.IRegisterDao;
import org.fisco.bcos.Service.Interface.IRegisterService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    private IRegisterDao registerDao;

    @Override
    public JSONObject register()  throws Exception{

        try {
            return registerDao.register();
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("checked", false);
            object.put("address", "none");
            return object;
        }
    }
}
