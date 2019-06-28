package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IUnsellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnsellController {
    @Autowired
    private IUnsellService unsellService;

    @RequestMapping(value = "/user/pet/unsell",method = RequestMethod.POST)
    private JSONObject sell(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        int petId = object.getInteger("petId");
        return unsellService.unsell(address,petId);
    }
}
