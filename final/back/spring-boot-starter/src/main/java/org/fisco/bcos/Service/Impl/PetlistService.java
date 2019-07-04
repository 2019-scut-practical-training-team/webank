package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Contracts.*;
import org.fisco.bcos.Dao.Interface.IPetListDao;
import org.fisco.bcos.Service.Interface.IPetlistService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class PetlistService implements IPetlistService {


    @Autowired
    private IPetListDao petListDao;

    @Override
    public JSONObject getPetlist(String key) {


        try {
            return petListDao.petList(key);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("petList", "none");
            return object;
        }
    }
}
