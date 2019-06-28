package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.service.ISigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Autowired
    private ISigninService sservice ;

    @PostMapping("/test1")
    public String hello1(String s){
        JSONObject object = JSON.parseObject(s);
        return object.getString("name")+String.valueOf(object.getInteger("int"));
    }

    @RequestMapping(value = "/signin",method = RequestMethod.POST)
    public boolean hello2(@RequestBody String s){
        JSONObject object = JSON.parseObject(s);
        if(object.getInteger("zpy")==123)
        {
           return sservice.signin(object.getString("check"));
        }
        else
            return false;
    }



}
