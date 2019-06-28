package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {
    @Autowired
    private ICheckService checkService ;

    @RequestMapping("/api/user/order/check")
    public JSONObject check(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        return checkService.check(object.getString("address"));
    }
}
