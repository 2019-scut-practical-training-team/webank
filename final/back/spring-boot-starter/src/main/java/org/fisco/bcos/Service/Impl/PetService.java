package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IPetService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value = "petService")
public class PetService implements IPetService {
    @Autowired
    private Web3j web3j;
    @Autowired
    Credentials credentials;
    @Autowired
    private Variables variables;

    @Override
    public JSONObject pets() throws Exception{
        JSONObject send = new JSONObject();
        send.put("petslist", callShowPetOnSell());
        return send;
    }
    public String callShowPetOnSell() throws Exception {
        Market market = Market.load(variables.getMarket(), web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
//        String[] s = market.showPetOnSell().send().split(",");
//        // TODO
//        JSONArray jsonArray = new JSONArray();
//        for (int i = 0; i < s.length; i=i+7) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("petId", s[i+1]);
//            jsonObject.put("petType", s[i]);
//            jsonObject.put("petPrice", s[i+2]);
//            jsonObject.put("petId", s[i+3]);
//            jsonObject.put("petId", s[i+4]);
//            jsonObject.put("petId", s[i+5]);
//            jsonObject.put("petId", s[i+6]);
//            jsonArray.add(jsonObject);
//        }
//        return jsonArray.toJSONString();
        //return  market.showPetOnSell().send();
        return "fuck";
    }
}
