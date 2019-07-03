package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Service.Interface.IPetService;
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


@Service(value = "petService")
public class PetService implements IPetService {
    @Autowired
    private Web3j web3j;
    @Autowired
    private Variables variables;

    @Override
    public JSONObject pets() throws Exception{
        Credentials credentials = GenCredential.create(variables.getAdmin());
        Market market = Market.load(variables.getMarket(), web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));

        JSONObject send = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        List index = market.showPetOnSell().send();

        for (int i = 0; i < index.size(); i++){
            JSONObject jsonObject = new JSONObject();

            BigInteger id = new BigInteger(index.get(i).toString());
            Tuple7<String, String, BigInteger, String, BigInteger, String, String> t = market.getPetByIndex(id).send();
            jsonObject.put("petId", t.getValue1());
            jsonObject.put("petType", t.getValue2());
            jsonObject.put("petPrice", t.getValue3());
            jsonObject.put("petName", t.getValue4());
            jsonObject.put("petImg", t.getValue5());
            jsonObject.put("petIntro", t.getValue6());
            jsonObject.put("owner", t.getValue7());
            jsonArray.add(jsonObject);
        }

        send.put("petslist", jsonArray);

        return send;
    }
}
