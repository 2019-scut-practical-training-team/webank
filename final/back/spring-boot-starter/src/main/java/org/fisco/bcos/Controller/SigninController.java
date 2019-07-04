package org.fisco.bcos.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.fisco.bcos.Service.Interface.ISigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SigninController {
    @Autowired
    private ISigninService siService;

    @PostMapping(value = "/signin")
    private JSONObject signin(@RequestBody String input)throws  Exception{
        JSONObject object = JSON.parseObject(input);
        String key = object.getString("key");

        return siService.signin(key);
    }

}
