package com.example.demo.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BalanceController {
    @Autowired
    private IBalanceService balanceService;

    @RequestMapping(value = "/user/balance",method = RequestMethod.POST)
    private JSONObject getBalance(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        return balanceService.getBalance(address);
    }

}
