package org.fisco.bcos.Service.Interface;

import com.alibaba.fastjson.JSONObject;

public interface IChangeInfoService {
    public JSONObject changeInfo(String address, String petId, String petType, int petPrice,
                                 String petName, String petImg, String petIntro);
}
