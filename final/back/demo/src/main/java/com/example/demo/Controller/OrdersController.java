package com.example.demo.Controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.Service.Interface.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
    @Autowired
    private IOrdersService ordersService ;

    @RequestMapping("/api/market/orders")
    public JSONObject orders() {
        return ordersService.orders();
    }
}