package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Bean.PetsListItem;
import com.example.demo.Service.Interface.ICreatePetService;
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
