package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Contracts.OrderContract;
import org.fisco.bcos.Dao.Interface.IRefundDao;
import org.fisco.bcos.Dao.Interface.IReturnDao;
import org.fisco.bcos.Service.Interface.IRefundService;
import org.fisco.bcos.Variables;
import org.fisco.bcos.constants.GasConstants;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.gm.GenCredential;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "refundService")
public class RefundService implements IRefundService {

    @Autowired
    private IRefundDao refundDao;

    @Override
    public JSONObject refund(String orderid, int op) throws Exception{
        try {
            TransactionReceipt transactionReceipt = refundDao.refund(orderid, op);
            JSONObject send = new JSONObject();

            if (transactionReceipt.getStatus().equals("0x0")){
                send.put("checked",true);
            }
            else if (transactionReceipt.getStatus().equals("0x16")){
                orderContract.rejectReturn(orderid).send();
                send.put("checked",false);
            }
            return send;
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("checked", "error");
            return object;
        }
    }
}
