package com.example.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IRefundListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefundListController {
    @Autowired
    private IRefundListService refundListService ;

    @RequestMapping("/api/market/refundlist")
    public JSONObject refundList() {
        return refundListService.refundList();
    }
}
