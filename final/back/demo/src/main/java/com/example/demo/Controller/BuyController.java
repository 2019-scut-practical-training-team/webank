package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IBuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuyController {
    @Autowired
    private IBuyService buyService ;

    @RequestMapping("/api/user/buy")
    public JSONObject refund(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        String address = object.getString("address");

        JSONObject subJson = object.getJSONObject("pet");
        String petId = subJson.getString("petId");
        int petPrice = subJson.getInteger("petPrice");
        String owner = subJson.getString("owner");

        return buyService.buy(address, petId, petPrice, owner);
    }
}
