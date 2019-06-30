package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.IRefundListService;
import org.springframework.stereotype.Service;

@Service(value = "refundListService")
public class RefundListService implements IRefundListService {
    @Override
    public JSONObject refundList(){
        JSONObject send = new JSONObject();
        return send;
    }
}
