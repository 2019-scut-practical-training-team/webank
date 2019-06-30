package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Service.Interface.ICreatePetService;
import org.springframework.stereotype.Service;

@Service
public class CreatePetService implements ICreatePetService {
    @Override
    public JSONObject createPet(String address, PetsListItem item) {
        JSONObject object = new JSONObject();
        object.put("checked",true);
        return object;
    }
}
