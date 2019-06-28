package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Bean.PetsListItem;
import com.example.demo.Service.Interface.ICreatePetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreatePetController {
    @Autowired
    private ICreatePetService createPetService;

    @RequestMapping(value = "/user/pet/createpet",method = RequestMethod.POST)
    private JSONObject createPet(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        PetsListItem item = object.getObject("pet",PetsListItem.class);
        return createPetService.createPet(address,item);
    }
}
