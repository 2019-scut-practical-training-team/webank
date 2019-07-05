package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

    private String market = "0xf0c0b4879bb8bbf9aa71e02b54e74e30e0240237";
    private String order = "0xb1d982f8d8ff180e55a319e5ac78275ad8edb617";
    private String admin = "2652b364efebbf7de39d0d28f30ff921a8fe18165fe1276e8990a9cb4ca1c8d6";  // can not use '0x' as prefix


    public String getMarket() {
        return market;
    }

    public void setMarket(String bumarket) {
        this.market = market;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

}
