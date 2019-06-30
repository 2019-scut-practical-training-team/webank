package org.fisco.bcos.Controller.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Service.Interface.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private IBalanceService balanceService;

    @Autowired
    private IBuyService buyService ;

    @Autowired
    private IChangeInfoService changeInfoService ;

    @Autowired
    private ICheckService checkService ;

    @Autowired
    private ICreatePetService createPetService;

    @Autowired
    private IPetlistService petlistService;

    @Autowired
    private ISellService sellService;

    @Autowired
    private IUnsellService unsellService;

    @RequestMapping(value = "/balance",method = RequestMethod.POST)
    private JSONObject getBalance(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        return balanceService.getBalance(address);
    }

    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    private JSONObject refund(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        String address = object.getString("address");

        JSONObject subJson = object.getJSONObject("pet");
        String petId = subJson.getString("petId");
        int petPrice = subJson.getInteger("petPrice");
        String owner = subJson.getString("owner");

        return buyService.buy(address, petId, petPrice, owner);
    }

    @RequestMapping("/pet/changeinfo")
    public JSONObject changeInfo(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        String address = object.getString("address");

        JSONObject subJson = object.getJSONObject("pet");
        String petId = subJson.getString("petId");
        String petType = subJson.getString("petType");
        int petPrice = subJson.getInteger("petPrice");
        String petName = subJson.getString("petName");
        String petImg = subJson.getString("petImg");
        String petIntro = subJson.getString("petIntro");

        return changeInfoService.changeInfo(address, petId, petType, petPrice, petName, petImg, petIntro);
    }

    @RequestMapping("/order/check")
    public JSONObject check(@RequestBody String s) {
        JSONObject object = JSONObject.parseObject(s);
        return checkService.check(object.getString("address"));
    }


    @RequestMapping(value = "/pet/createpet",method = RequestMethod.POST)
    private JSONObject createPet(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        PetsListItem item = object.getObject("pet",PetsListItem.class);
        return createPetService.createPet(address,item);
    }

    @RequestMapping(value = "/pet/petslist", method = RequestMethod.POST)
    private JSONObject getPetslist(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        return petlistService.getPetlist("address");
    }

    @RequestMapping(value = "/pet/sell",method = RequestMethod.POST)
    private JSONObject sell(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        int petId = object.getInteger("petId");
        return sellService.sell(address,petId);
    }

    @RequestMapping(value = "/pet/unsell",method = RequestMethod.POST)
    private JSONObject unsell(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("address");
        int petId = object.getInteger("petId");
        return unsellService.unsell(address,petId);
    }

}
