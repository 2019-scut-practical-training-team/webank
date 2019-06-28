package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetsController {
    @Autowired
    private IPetService petsService ;

    @RequestMapping("/api/market/pets")
    public JSONObject pets() {
        return petsService.pets();
    }
}