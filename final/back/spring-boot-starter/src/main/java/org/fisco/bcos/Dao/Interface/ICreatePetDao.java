package org.fisco.bcos.Dao.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;

public interface ICreatePetDao {
    public TransactionReceipt createPet(String key, PetsListItem item)throws Exception;
}
