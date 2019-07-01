package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Service.Interface.IPetService;
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
    @Autowired Credentials credentials;

    @Override
    public JSONObject pets() throws Exception{
        JSONObject send = new JSONObject();
        send.put("petService", callShowPetOnSell());
        return send;
    }
    public String callShowPetOnSell() throws Exception {
        String contract = "3d7bfc7b9cca1a7a78c23ac90fe165cb9f2d8a19";
        Market market = Market.load(contract, web3j, credentials, new StaticGasProvider(GasConstants.GAS_PRICE, GasConstants.GAS_LIMIT));
        String s = market.showPetOnSell().send();
        // TODO
        return s;
    }
}
