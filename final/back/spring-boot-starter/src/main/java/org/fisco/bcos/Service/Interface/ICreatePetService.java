package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;

public interface ICreatePetService {
    public JSONObject createPet(String address, PetsListItem item)throws Exception;
}
