package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import groovy.lang.Tuple;
import org.fisco.bcos.Contracts.Market;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Impl.OrdersDao;
import org.fisco.bcos.Service.Interface.IOrdersService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tuples.generated.Tuple7;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service(value = "ordersService")
public class OrdersService implements IOrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public JSONObject orders() throws Exception {
        try {
            return ordersDao.orders();
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("orderList", "error");
            return object;
        }
    }
}
