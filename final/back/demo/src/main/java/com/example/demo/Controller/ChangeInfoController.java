package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IChangeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangeInfoController {
    @Autowired
    private IChangeInfoService changeInfoService ;

    @RequestMapping("/api/user/pet/changeinfo")
    public JSONObject changeInfo(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        String address = object.getString("address");

        JSONObject subJson = object.getJSONObject("pet");
        String petId = subJson.getString("petId");
        String petType = subJson.getString("petType");
        int petPrice = subJson.getInteger("petPrice");
        String petName = subJson.getString("petName");
        String petImg = subJson.getString("petImg");
        String petIntro = subJson.getString("petIntro");

        return changeInfoService.changeInfo(address, petId, petType, petPrice, petName, petImg, petIntro);
    }
}
