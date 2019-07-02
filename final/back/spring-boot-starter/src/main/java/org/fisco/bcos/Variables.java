package org.fisco.bcos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "variables")
public class Variables {

    private String market = "0xc241542c7d42a8b4d5104f1605c03dc34b04b6e3";
    private String order = "0x025348f312876f2fca5d50c9e708798afa7a25b6";
    private String admin = "2d1c2c11dc75d2b7eb401cd1ef90592a3bc1425e7e92cb1d98425b9b379e609b";  // can not use '0x' as prefix

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
