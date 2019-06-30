package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IRegisterService;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Override
    public JSONObject register() {
        JSONObject object = new JSONObject();


        EncryptType.encryptType = 0;
        Credentials credentials = GenCredential.create();
        String address = credentials.getAddress();
        String privateKey = credentials.getEcKeyPair().getPrivateKey().toString(16);
        String publicKey = credentials.getEcKeyPair().getPublicKey().toString(16);

        object.put("checked",true);
        object.put("key",privateKey);
        return object;
    }
}
