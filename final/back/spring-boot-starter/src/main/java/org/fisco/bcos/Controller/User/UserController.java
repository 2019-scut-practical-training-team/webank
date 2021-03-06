package org.fisco.bcos.Controller.User;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Bean.PetsListItem;
import org.fisco.bcos.Service.Impl.IfCreatedService;
import org.fisco.bcos.Service.Impl.ReturnService;
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

    @Autowired
    private IReturnService returnService;

    @Autowired
    private IIfCreatedService ifCreatedService;

    @RequestMapping(value = "/balance",method = RequestMethod.POST)
    private JSONObject getBalance(@RequestBody String input)throws Exception{
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");
        return balanceService.getBalance(key);
    }

    @RequestMapping(value = "/buy",method = RequestMethod.POST)
    private JSONObject buy(@RequestBody String s) throws Exception{
        JSONObject object = JSONObject.parseObject(s);
        String key = object.getString("key");
        String petId = object.getString("petId");

        return buyService.buy(key, petId);
    }

    @RequestMapping("/pet/changeinfo")
    public JSONObject changeInfo(@RequestBody String s) throws Exception{
        JSONObject object = JSONObject.parseObject(s);
        String address = object.getString("key");

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
    public JSONObject check(@RequestBody String s) throws Exception{
        JSONObject object = JSONObject.parseObject(s);
        return checkService.check(object.getString("key"));
    }

    @RequestMapping("/return")
    public JSONObject returnOrder(@RequestBody String s) throws Exception{
        JSONObject object = JSONObject.parseObject(s);
        return returnService.returnOrder(object.getString("key"),object.getInteger("orderId"),object.getString("reason"));
    }


    @RequestMapping(value = "/pet/createpet",method = RequestMethod.POST)
    private JSONObject createPet(@RequestBody String input)throws Exception{
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");
        PetsListItem item = object.getObject("pet",PetsListItem.class);

        return createPetService.createPet(key,item);
    }

    @RequestMapping(value = "/pet/petslist", method = RequestMethod.POST)
    private JSONObject getPetslist(@RequestBody String input)throws Exception{
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");
        return petlistService.getPetlist(key);
    }

    @RequestMapping(value = "/pet/sell",method = RequestMethod.POST)
    private JSONObject sell(@RequestBody String input)throws Exception{
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");
        int petId = object.getInteger("petId");
        return sellService.sell(key,petId);
    }

    @RequestMapping(value = "/pet/unsell",method = RequestMethod.POST)
    private JSONObject unsell(@RequestBody String input)throws Exception{
        JSONObject object = JSON.parseObject(input);
        String address = object.getString("key");
        int petId = object.getInteger("petId");
        return unsellService.unsell(address,petId);
    }

    @RequestMapping(value = "/ifcreated",method = RequestMethod.POST)
    private JSONObject ifCreated(@RequestBody String input){
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");
        return ifCreatedService.ifCreated(key);
    }


}
