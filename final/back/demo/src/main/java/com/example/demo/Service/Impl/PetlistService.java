package com.example.demo.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Bean.PetsListItem;
import com.example.demo.Service.Interface.IPetlistService;
import org.springframework.stereotype.Service;

@Service
public class PetlistService implements IPetlistService {

    @Override
    public JSONObject getPetlist(String address) {
        JSONObject object = new JSONObject();
        PetsListItem[] list = new PetsListItem[2];
        list[0] = new PetsListItem(1,"仓鼠",200,"米粒",0,"www.abc.com","这是一个仓鼠");
        list[1] = new PetsListItem(2,"蜘蛛",600,"黑寡妇",1,"www.abc.com","这是一个大蜘蛛");
        object.put("petsList",list);
        return object;
    }
}
