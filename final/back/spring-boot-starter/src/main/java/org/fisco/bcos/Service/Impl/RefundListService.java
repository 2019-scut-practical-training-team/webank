package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Impl.RefundListDao;
import org.fisco.bcos.Service.Interface.IRefundListService;
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

@Service(value = "refundListService")
public class RefundListService implements IRefundListService {

    @Autowired
    private RefundListDao refundListDao;

    @Override
    public JSONObject refundList() throws Exception{
        try {
            return refundListDao.refundList();
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("orderList", "error");
            return object;
        }
    }
}
