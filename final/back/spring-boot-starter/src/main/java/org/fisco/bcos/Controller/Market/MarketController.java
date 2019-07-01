package org.fisco.bcos.Controller.Market;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IOrdersService;
import org.fisco.bcos.Service.Interface.IPetService;
import org.fisco.bcos.Service.Interface.IRefundListService;
import org.fisco.bcos.Service.Interface.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/market")
public class MarketController {
    @Autowired
    private IOrdersService ordersService ;

    @Autowired
    private IPetService petsService ;

    @Autowired
    private IRefundService refundService ;

    @Autowired
    private IRefundListService refundListService ;

    @RequestMapping("/refundlist")
    public JSONObject refundList()throws Exception  {
        return refundListService.refundList();
    }

    @RequestMapping("/refund")
    public JSONObject refund(@RequestBody String s) throws Exception {
        JSONObject object = JSONObject.parseObject(s);
        return refundService.refund(object.getString("orderId"));
    }

    @RequestMapping("/pets")
    public JSONObject pets() throws Exception {
        return petsService.pets();
    }

    @RequestMapping("/orders")
    public JSONObject orders()throws Exception  {
        return ordersService.orders();
    }
}