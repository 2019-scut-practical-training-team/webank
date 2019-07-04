package org.fisco.bcos.Service.Impl;

import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Dao.Interface.IIfCreatedDao;
import org.fisco.bcos.Service.Interface.IIfCreatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IfCreatedService implements IIfCreatedService {
    @Autowired
    private IIfCreatedDao ifCreatedDao;

    @Override
    public JSONObject ifCreated(String key)   {
        try {
            return ifCreatedDao.ifCreated(key);
        }
        catch (Exception e){
            JSONObject object = new JSONObject();
            object.put("checked", "error");
            return object;
        }
    }
}
