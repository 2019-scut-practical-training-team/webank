package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IPetlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetlistController {
    @Autowired
    private IPetlistService petlistService;

    @RequestMapping(value = "/user/pet/petslist", method = RequestMethod.POST)
    private JSONObject getBalance(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        return petlistService.getPetlist("address");
    }
}
