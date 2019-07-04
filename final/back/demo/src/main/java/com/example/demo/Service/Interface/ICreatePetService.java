package com.example.demo.Service.Interface;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Bean.PetsListItem;

public interface ICreatePetService {
    public JSONObject createPet(String address, PetsListItem item);
}
