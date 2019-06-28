package com.example.demo.service.impl;

import com.example.demo.service.ISigninService;
import org.springframework.stereotype.Service;

@Service(value = "sservice")
public class SigninService implements ISigninService {
    @Override
    public boolean signin(String key){
        return true;
    }
}
