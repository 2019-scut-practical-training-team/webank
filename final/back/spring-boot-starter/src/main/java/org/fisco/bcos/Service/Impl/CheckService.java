package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Impl.CheckDao;
import org.fisco.bcos.Service.Interface.ICheckService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service(value = "checkService")
public class CheckService implements ICheckService {
    @Autowired
    private CheckDao checkDao;

    @Override
    public JSONObject check(String key) throws Exception{
        try {
            return checkDao.check(key);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("orderList", "error");
            return object;
        }
    }

}
