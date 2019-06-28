package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.ISellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SellController {
    @Autowired
    private ISellService sellService;

    @RequestMapping(value = "/user/pet/sell",method = RequestMethod.POST)
    private JSONObject sell(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        int petId = object.getInteger("petId");
        return sellService.sell(address,petId);
    }
}
