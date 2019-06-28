package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefundController {
    @Autowired
    private IRefundService refundService ;

    @RequestMapping("/api/market/refund")
    public JSONObject refund(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        return refundService.refund(object.getString("orderId"), object.getInteger("orderStatus"));
    }
}
